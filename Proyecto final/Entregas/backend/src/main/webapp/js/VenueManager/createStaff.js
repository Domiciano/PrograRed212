const id = document.getElementById("sId");
const nameTF = document.getElementById("sName");
const lastname = document.getElementById("sLastname");
const password = document.getElementById("sPass");
const addBtn = document.getElementById("addBtn");
const cBtn = document.getElementById("cBtn");
const select = document.getElementById("select");
let userLoged = JSON.parse(window.localStorage.getItem('user'));
var ncities;
//logout
const logoutBtn = document.getElementById("logoutBtn");



const postStaff = async ()=>{

      var str = select.options[select.selectedIndex].text;
      var venueId = getVenueId(str);
    let user = {

        id:id.value,
        name: nameTF.value,
        lastName:lastname.value,
        password:password.value,
        venuesBuddyID:venueId,
        roleBuddyID:3
    
    };

    console.log(user);
     
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
        swal("Datos Actualizados", "Creaste un nuevo miembro del staff", "success");
     }     
}


const getVenueId = (vname)=>{  
  
    for(let i in venuesD){
        if(venuesD[i].name == vname){
            
            return venuesD[i].id;
        }       
    }

}

const getAllVenues = async ()=>{

    let venuesNames = await fetch("http://localhost:8080/backend/api/venues/getvenues/");
    let venues = await venuesNames.json();
    venuesD = venues;
      
}




const getVenuesByCity = async ()=>{
    

    console.log(userLoged.city);
    let cityId = getCityId(userLoged.city);
    console.log("ID Ciudad: "+cityId);

    let venuesNames = await fetch("http://localhost:8080/backend/api/venues/getvenues/"+cityId);
    let venues = await venuesNames.json();
    let html = `<option selected disabled selected hidden>Seleccionar Sede</option>`;
    for(let i in venues){
        let incomingName = venues[i].name;
            html += `<option value="${i}">${incomingName}</option>`;        
    }
    select.innerHTML = html;
    

}
const getAllCities = async ()=>{
    let citiesNames = await fetch("http://localhost:8080/backend/api/cty/cities");
    let cities = await citiesNames.json();
    ncities = cities;
    getVenuesByCity();

      
}
const getCityId = (vname)=>{  
  
    for(let i in ncities){
        if(ncities[i].name == vname){
            
            return ncities[i].id;
        }       
    }

}


const clearFields = async()=>{
    id.value = "";
    nameTF.value = "";
    lastname.value = "";
    password.value = "";
    select.value = "Seleccionar Sede";
   

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
logoutBtn.addEventListener('click', ()=>{
    window.location.href = "index.html";
    userLoged = undefined;
  })

getAllCities();
getAllVenues();



