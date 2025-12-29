resource "aws_cloudwatch_log_group" "accountbalance_api_logs" {
  name              = "/ecs/accountbalance-api"
  retention_in_days = 7
}
