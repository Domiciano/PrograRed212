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

const getAllTasks = async () => {
    let response = await fetch("https://foltrestfirstapp.herokuapp.com/api/task/all")
    let data = await response.json();
    toDo.innerHTML = "";
    doing.innerHTML = "";
    done.innerHTML = "";
    for(let i in data){
        let task = data[i];
        let taskView = new Task(task);
        if(task.type === "TO DO"){
            taskView.render(toDo);
        }
        else if(task.type === "DOING"){
            taskView.render(doing);
        }
        else if(task.type === "DONE"){
            taskView.render(done);
        }
    }
}