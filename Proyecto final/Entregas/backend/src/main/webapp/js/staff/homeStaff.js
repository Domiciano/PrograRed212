var clients = document.getElementById("clients");

const getClients = async () => {
    console.log("i'm in")
    let response = await fetch("http://localhost:8080/backend/api/cls/getCCMP")
    console.log("Fetch done")
    let data = await response.json();
    console.log(data[0])
    clients.innerHTML = "";
    for(let i in data){
        let CCMP = data[i];
        let CCMPView = new CCMPview(CCMP);
        CCMPView.render(lastClients);
    }
}

getClients();