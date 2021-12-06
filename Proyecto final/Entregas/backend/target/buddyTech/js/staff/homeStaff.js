var lastClients = document.getElementById("lastClients");

const getClients = async () => {
    let response = await fetch("http://localhost:8080/backend/api/cls/getCCMP")
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