var clients = document.getElementById("clients");

const getClients = async () => {
    let response1 = await fetch("http://localhost:8080/backend/api/cls/getclients")
    let data1 = await response1.json();
    clients.innerHTML = "";
    for(let i in data1){
        let client = data1[i];
        console.log(client);
        let response2 = await fetch("http://localhost:8080/backend/api/ms/searchmembership/" + client.membershipID)
        let data2 = await response2.json();
        let membership = data2[0];

        console.log(membership);

        let response3 = await fetch("http://localhost:8080/backend/api/ps/searchplan/" + membership.planID)
        let data3 = await response3.json();

        let plan = data3[0];
        let cmpView = new CMP(client, membership, plan);
        cmpView.render(clients);
    }
}

getClients();