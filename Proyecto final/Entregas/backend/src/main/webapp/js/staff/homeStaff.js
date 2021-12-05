var lastClients = document.getElementById("lastClients");

var title = document.getElementById("title");
var description = document.getElementById("description");
var addBtn = document.getElementById("addBtn");

addBtn.addEventListener("click", async ()=>{
    let task = {
        title: title.value,
        description: description.value
    };
    
    let json = JSON.stringify(task);

    let response = await fetch("https://foltrestfirstapp.herokuapp.com/api/task/create", {
        method: "POST",
        headers: {
            "Content-Type":"application/json"
        },
        body: json
    });
    if(response.ok){
        let data = await response.json();
        console.log(data);
        title.value = "";
        description.value = "";
        getAllTasks();
    }

});

const getClients = async() =>{
    let response = await fetch("http://localhost:8080/backend/api/cls/all")
    let data = await response.json();
    return data;
}

const lastClients = async () => {
    let response = await fetch("http://localhost:8080/backend/api/cls/all")
    let data = await response.json();
    lastClients.innerHTML = "";
    for(let i in data){
        let client = data[i];
        let clientView = new Client(client);
        if(task.type === "TO DO"){
            clientView.render(lastClients);
        }
    }
}