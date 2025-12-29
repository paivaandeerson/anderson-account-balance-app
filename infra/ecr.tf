resource "aws_ecr_repository" "api_repo" {
  name = "anderson-account-balance-app"
  image_tag_mutability = "MUTABLE"
  force_delete = true
}

output "ecr_repository_url" {
  value = aws_ecr_repository.api_repo.repository_url
}