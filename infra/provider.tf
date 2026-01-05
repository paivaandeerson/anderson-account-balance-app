terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = "us-east-1" 
}

terraform {
  backend "s3" {
    bucket = "anderson-account-balance-state" 
    key    = "terraform/state.tfstate"
    region = "us-east-1"
    # dynamodb_table = "state_locking"  #avoids race condition (co-worker), it's required to provide a table with lockID
  }
}