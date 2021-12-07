
const cardsC = document.getElementById("cardsContainer");
/*
const updateTask = async (task)=>{
  
    
    let json = JSON.stringify(task);
    //let obj = JSON.parse(json);

    let response = await fetch("https://trelloappalejandro.herokuapp.com/api/tasks/update", 
        {
            method: "PUT",
            headers: {
                "Content-Type":"application/json"
            },
            body: json
        }
    );
    if(response.ok){
        let data = await response.json();
        console.log(data);
        getAllTasks();
    }
    
}
*/
/*
const deleteTask = async (x)=>{
    
    let json = JSON.stringify(x);
    //let obj = JSON.parse(json);

    let response = await fetch("https://trelloappalejandro.herokuapp.com/api/tasks/delete", 
        {
            method: "DELETE",
            headers: {
                "Content-Type":"application/json"
            },
            body: json
        }
    );
    if(response.ok){
        let data = await response.json();
        console.log(data);
        getAllTasks();
    } 
}
*/

const getAllUsers = async ()=>{
    let response = await fetch("http://localhost:8080/backend/api/users/");
    let data = await response.json();
    cardsC.innerHTML = "";
    

    
    for(let i in data){
        let user = data[i];     
        let taskView = new staffView(user);
        let view = taskView.render();

        
         //let view = taskView.render();
        
       cardsC.appendChild(view);
        

        
        
        
        console.log(taskView);
        //usersContainer.innerHTML += `<li>${user.name}</li>`;
    }
}

getAllUsers();

