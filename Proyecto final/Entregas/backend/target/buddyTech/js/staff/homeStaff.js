var clientsContainer = document.getElementById("clientsContainer");

<<<<<<< HEAD
const getCardInfo = async() => {
    clientsContainer.innerHTML = "";
=======
$(document).ready(function(){
    $('[data-bs-toggle="popover"]').popover();
});

const getCardInfo = async () => {
    
>>>>>>> 4d7be6e275a0b6c0650260ef73c1392a41f9ca39
    let response1 = await fetch("http://localhost:8080/backend/api/cls/getclients")
    let clients = await response1.json();
    for (let i in clients) {
        let client = clients[i];
        let response2 = await fetch("http://localhost:8080/backend/api/cls/cardInfo/" + client.natId)
        let card = await response2.json();
        let cardView = new Card(card[0]);
        cardView.render(clientsContainer);
    }
}

getCardInfo();