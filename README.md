# graphQL-demo
This project is to quickly become familiar with the integration of MVC structure and DGS—qraphlQL with MongoDB and related tests.

## TO RUN 

### setup local mongo using docker
- docker images -> check the mongo images installsed or not
- docker ps -> if there is any container running[[Docker]]
-   `if not`: docker run -itd --name dockername -p 27017:27017 mongo --auth
-   docker exec -it mongo-docker mongo admin
-  建立一个用户有权限编辑
    1. `db.createUser({ user:'admin',pwd:'root',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});`
    2. `db.auth("admin", "root")`
		
### Run the program
