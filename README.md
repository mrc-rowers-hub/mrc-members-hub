# Row your boat

🛠️ WIP

A frontend for all MRC rowing services

## Running

You need to set the following environment variable: 
- `SPRING_PROFILES_ACTIVE` - `local`, or `ec2`

Running locally:
- in IntelliJ, get the .envfile plugin
- add your env variables to this 


- Running on 8082, to connect with services on 8081 and 8080

Running in EC2: 
- set env var: `export SPRING_PROFILES_ACTIVE=ec2`
- Other services must be running 
