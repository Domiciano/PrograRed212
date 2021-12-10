const cardsC = document.getElementById("cardsContainer");
const createBtn = document.getElementById("createBtn");
const nameTF = document.getElementById("name");
const amountF = document.getElementById("amountF");
const amountT = document.getElementById("amountT");
const statusTF = document.getElementById("status");
const cancelBtn = document.getElementById("cButton");
const searchBtn = document.getElementById("sButton");


const getCardInfo = async () => {
    
    let response1 = await fetch("http://localhost:8080/backend/api/cls/getclients")
    let clients = await response1.json();
    for(let i in clients){
        let client = clients[i];
        let response2 = await fetch("http://localhost:8080/backend/api/cls/cardInfo/" + client.natId)
        let cardsPlan = await response2.json();
        let cardView = new Card(cardsPlan[0]);
        cardView.render(cardsC);   
    }
}

const getClientsByParam = async ()=>{
    let filter = checkFilter();
    let response = await fetch("http://localhost:8080/backend/api/getclients/"+filter.nameO+"-"+filter.amountf0+"-"+filter.amountt0+"-"+filter.status0);
    let clients = await response.json();
    cardsC.innerHTML = "";
    for(let i in clients){
        let client = clients[i];

    }
}

const checkFilter = ()=>{

    let n ="";
    let af = ""
    let at = "";
    let s = "";

      if(nameTF.value == ""){
         n = "null";
      }else{
        n = nameTF.value;
      }
      if(amountF.value ==""){
         af = "null"; 
      }else{
         af= amountF.value;
      }
      if(amountT.value == ""){
           at = "null";
      }else{
          at = amountT.value;
      }
    let obj = {
           
           name0: n,
           amountf0 : af,
           amountt0: at,
           status0: s
    };

    return obj
}

const clearFields = async()=>{
    nameTF.value = "";
    amountF.value = "";
    amountF.value = "";
    statusTF.value = "";
}

createBtn.addEventListener("click", (event)=>{
event.preventDefault();
});

searchBtn.addEventListener("click", (event)=>{
    event.preventDefault();
    getClientsByParam();
});

cancelBtn.addEventListener("click", (event)=>{
    event.preventDefault();
    clearFields();
});

clearFields();
getCardInfo();