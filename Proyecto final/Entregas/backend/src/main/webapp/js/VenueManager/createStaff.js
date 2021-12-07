const id = document.getElementById("sId");
const nameTF = document.getElementById("sName");
const lastname = document.getElementById("sLastname");
const password = document.getElementById("sPass");
const venueId = document.getElementById("sVenueId");
const addBtn = document.getElementById("addBtn");
const cBtn = document.getElementById("cBtn");
const plansDrop = document.getElementById("plansDrop");
const sede = document.getElementById("sede");

const postStaff = async ()=>{
    let user = {

        id:id.value,
        name: nameTF.value,
        lastName:lastname.value,
        password:password.value,
        venuesBuddyID:venueId.value,
        roleBuddyID:3
    
    };
    

    let json = JSON.stringify(user);
    //let obj = JSON.parse(json);

    let response = await fetch("http://localhost:8080/backend/api/users/", 
        {
            method: "POST",
            headers: {
                "Content-Type":"application/json",
                
            },
            body: json
        }
    );
    if(response.ok){
        let data = await response.json();
        console.log(data);
        
    }
}
const getVenues = async ()=>{
    let venuesNames = await fetch("http://localhost:8080/backend/api/venues/getvenues");
    let venues = await venuesNames.json();
    console.log(venues);
    let html = `<option selected disabled selected hidden>Seleccionar Ciudad...</option>`;
    for(let i in venues){
        let incomingName = venues[i].name;
            html += `<option value="${i}">${incomingName}</option>`;        
    }
    plansDrop.innerHTML = html;
}
getVenues();
const clearFields = async()=>{
    id.value = "";
    nameTF.value = "";
    lastname.value = "";
    password.value = "";
    venueId.value = "";

}

addBtn.addEventListener("click", (event)=>{
    event.preventDefault();
    postStaff();
    clearFields();
});

cBtn.addEventListener("click", (event)=>{
    event.preventDefault();
    clearFields();
    
});


plansDrop.addEventListener("click",(event)=>{

    event.preventDefault();
    sede.innerHTML = plansDrop.innerHTML;
})



