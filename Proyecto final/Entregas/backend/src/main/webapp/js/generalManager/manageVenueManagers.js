var subgerentesContainer = document.getElementById("subgerentesContainer");

const getCardInfo = async () => {

    let response1 = await fetch("http://localhost:8080/backend/api/users/cardInfo")
    let managerCards = await response1.json();
    for(let i in managerCards) {
        let managerCard = managerCards[i];
        //let response2 = await fetch("http://localhost:8080/backend/api/cls/cardInfo/" + client.natId)
        let cardView = new UserCard(managerCard);
        cardView.render(subgerentesContainer);

    }
}

getCardInfo();