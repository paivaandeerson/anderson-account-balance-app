resource "aws_ecs_cluster" "main" {
  name = "accountbalance-api-cluster"
}

resource "aws_ecs_task_definition" "app" {
  family                   = "accountbalance-api-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256" # 0.25 vCPU
  memory                   = "512" # 512 MB
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([{
    name      = "accountbalance-api"
    image     = "${aws_ecr_repository.api_repo.repository_url}:latest"
    essential = true
    portMappings = [{
      containerPort = 8081
      hostPort      = 8081
    }]
    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = aws_cloudwatch_log_group.accountbalance_api_logs.name
        "awslogs-region"        = "us-east-1"
        "awslogs-stream-prefix" = "ecs"
      }
    }
  }])
}

resource "aws_ecs_service" "main" {
  name            = "accountbalance-api-service"
  cluster         = aws_ecs_cluster.main.id
  task_definition = aws_ecs_task_definition.app.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = [aws_subnet.public.id]
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true # Obrigatório se estiver em subnet pública sem NAT, ALB nao provisionado
  }
}