
const cardsC = document.getElementById("cardsContainer");
const userN = document.getElementById("nameU");
const lastname = document.getElementById("lastname");
const venue = document.getElementById("venue");
const id = document.getElementById("id");
const select = document.getElementById("select");
const filterbtn = document.getElementById("filter");
const cancelbtn = document.getElementById("cButton");
var venuesD;

const getAllUsers = async ()=>{


    let response = await fetch("http://localhost:8080/backend/api/users/"+"roleBuddyID-3");
    let data = await response.json();
    cardsC.innerHTML = "";
    

    
    for(let i in data){
        let user = data[i];     
        let sfView = new staffView(user);
        let view = sfView.render();  
       cardsC.appendChild(view);
    
    }
}

const getVenues = async ()=>{
    let venuesNames = await fetch("http://localhost:8080/backend/api/venues/getvenues");
    let venues = await venuesNames.json();
    venuesD = venues;
    let html = `<option selected disabled selected hidden>Seleccionar Sede</option>`;
    for(let i in venues){
        let incomingName = venues[i].name;
            html += `<option value="${i}">${incomingName}</option>`;        
    }
    select.innerHTML = html;
    
}
getVenues();

const getVenueId = (vname)=>{  
  
    for(let i in venuesD){
        if(venuesD[i].name == vname){
            
            return venuesD[i].id;
        }       
    }

}

const getUserByParam = async()=>{

    let parameters = checkFilterP();
    let values = checkFilterV();

    console.log("http://localhost:8080/backend/api/users/"+parameters+"-"+values);
    let response = await fetch("http://localhost:8080/backend/api/users/"+parameters+"-"+values);
    let data = await response.json();
    cardsC.innerHTML = "";
    

    
    for(let i in data){
        let user = data[i];     
        let sfView = new staffView(user);
        let view = sfView.render();

        
         //let view = taskView.render();
        
       cardsC.appendChild(view);
        
    }

}


const checkFilterV = ()=>{

    let values = "3,";

    if(!userN.value==""){

       values += userN.value+","
    }

    if(!lastname.value==""){
       values += lastname.value+","
    }
    if(select.options[select.selectedIndex].text!="Seleccionar Sede"){
        let str =select.options[select.selectedIndex].text;
        let id = getVenueId(str);
       values += id+","
    }
    if(!id.value==""){
       values += id.value+",";
    }
     return values;
}

const checkFilterP = ()=>{
    let properties = "roleBuddyID,";
    
    if(!userN.value==""){
       properties+="name,"
    }
    if(!lastname.value==""){
       properties+="lastName,"
    }
    if(select.options[select.selectedIndex].text!="Seleccionar Sede"){
       properties+="venuesBuddyID,"
    }
    if(!id.value==""){
 
       properties+="id,"
    }

    return properties;

}


const clearFields = async()=>{
    userN.value = "";
    lastname.value = "";
    id.value = "";
    select.value = "Seleccionar Sede";
   

}

filterbtn.addEventListener("click", (event)=>{
    
    event.preventDefault();
    getUserByParam();
    clearFields();
    
});

cancelbtn.addEventListener("click",(event)=>{

    event.preventDefault();
    clearFields();
})

clearFields();

getAllUsers();
