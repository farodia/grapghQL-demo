# graphQL-demo
This project is to quickly become familiar with the integration of MVC structure and DGS—qraphlQL with MongoDB and related tests.



## TO RUN 

### setup local mongo using docker
- `docker images` -> check the mongo images installsed or not
- `docker ps` -> if there is any container running[[Docker]]
	-   if not: `docker run -itd --name dockername -p 27017:27017 mongo --auth`
-   `docker exec -it mongo-docker mongo admin`
-   create a user with authentication
    1. `db.createUser({ user:'admin',pwd:'root',roles:[ { role:'userAdminAnyDatabase', db: 'admin'},"readWriteAnyDatabase"]});`
    2. `db.auth("admin", "root")`
		
### Run the program
POST:	`http://localhost:8091` to add a new book infomation

    {
	"isn": "1",
	"title": "England",
	"summary": "its a country"
	}
GET:    `http://localhost:8091` to show all books

### Have a try in QraphQL page
in `http://localhost:8091/graphiql`

          {	
		finds(titleFilter:""){ 
		isn
	  	title
	  	summary
	  	}
         }
