var clients = document.getElementById("clients");

const getClients = async () => {
    let response = await fetch("http://localhost:8080/backend/api/cls/getCCMP")
    let data = await response.json();
    clients.innerHTML = "";
    for(let i in data){
        let CCMP = data[i];
        let CCMPView = new CCMPview(CCMP);
        CCMPView.render(lastClients);
    }
}

getClients();