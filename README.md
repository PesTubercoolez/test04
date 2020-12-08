# How to properly set project for loading matrixes from DB
### The First step: 
Download Docker, install it, set configurations, and add PostgreSQL container
### The Second step: 
Start PostgreSQL container in Docker with Terminal
> Example:
```  
$ docker start 'your_postgres_container_id'
```
### The Third step: 
Create your database and its tables
> Example 1 : 
CREATE DATABASE your_database_name;

> Example 2 : CREATE TABLE your_table_name (
>
>id SERIAL PRIMARY KEY,
>
>first_column json,
>
>);
>
### The Fourth step: 
Set your database configuration in .yml file
> Example  
```
            version: '3.1'
            services:
            
              vdma_postgres:
                image: postgres:latest
                environment:
                  POSTGRES_DB: matrix_db
                  POSTGRES_USER: root
                  POSTGRES_PASSWORD: mypaswword
                ports:
                  - "5432:5432"
                volumes:
                  - vdma_postgres:/var/lib/postgresql/data            
            volumes:
              vdma_postgres:
```
### The Fifth step: 
Download docker composer and create the container that have your .yml file
>Example:
``` 
$ cd Folder with your .yml file
$ docker-compose up -d
```
### The Sixth step: 
Change DB config and CRUD statements if it's needed in 'constants -> DatabaseConstants' package
### The Seventh step:
Run your .yml file (it can be made from 'docker start' command in Terminal or from IDE) and Main()
# Enjoy

