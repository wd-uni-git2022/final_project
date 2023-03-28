# final_project

## Backend

### Optional

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