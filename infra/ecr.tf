resource "aws_ecr_repository" "api_repo" {
  name                 = "accountbalance-api"
  image_tag_mutability = "MUTABLE"
}

output "ecr_repository_url" {
  value = aws_ecr_repository.api_repo.repository_url
}