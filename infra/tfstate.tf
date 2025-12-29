terraform {
  backend "s3" {
    bucket         = "account-balance-state" 
    key            = "terraform/state.tfstate"
    region         = "us-east-1"
  }
}