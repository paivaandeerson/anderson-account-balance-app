terraform {
  backend "s3" {
    bucket         = "accoubt-balance-state" 
    key            = "terraform/state.tfstate"
    region         = "us-east-1"
  }
}