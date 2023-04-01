# final_project

## How to build and start for testing

1. Start the Postgres server from the `docker-compose.yml` file via `docker compose up`

2. Build the frontend via `npm install`, `npx quasar build` in the `/frontend` directory

    - The build directory has already been set to the backend `resources/META-INF/resources` directory in the config
  
3. Start the backend via `./mvnw compile quarkus:dev` in the `/backend` directory

4. Go to `http://localhost:8080/` to view the quiz app

5. On startup two test users get automatically created for login

    - Credentials:
      
        - email: `user@example.org`, password: `user`

        - email: `admin@example.org`, password: `admin`

6. There is also one example quiz already

7. Anonymous users can only view existing quiz names in the quiz list

8. Logged in users can play quizzes

9. Admins can create quizzes, edit the username of users and delete users

## Components

- Vue frontend

    - Quasar framework

- Quarkus backend

- Postgres database


## Features

- JWT auth with roles (admin & user role)

- Login and registration

- Logged in users can play quizzes

    - anonymous users not

    - display amount of correctly answered questions at the end of the quiz

- Admins can create new quizzes

- Admins can edit the username of users

- Admins can delete users

- Responsive -> Menu tabs transform to drawer on small window width

- GET, POST, PATCH, DELETE requests


## Optional

- Private and public key are already included in the project for JWT auth
  
    - However, for production use, you should generate a new pair of keys for proper security
      
      - Explained here: https://quarkus.io/guides/security-jwt#generating-a-jwt
      
      - Commands: 
      
        - ```
          # Generating keys
          openssl genrsa -out rsaPrivateKey.pem 2048
          openssl rsa -pubout -in rsaPrivateKey.pem -out publicKey.pem
        
          # Converting private key
          openssl pkcs8 -topk8 -nocrypt -inform pem -in rsaPrivateKey.pem -outform pem -out privateKey.pem      
          ```