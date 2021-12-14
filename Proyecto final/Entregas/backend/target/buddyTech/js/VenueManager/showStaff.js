
const cardsC = document.getElementById("cardsContainer");
const userN = document.getElementById("nameU");
const lastname = document.getElementById("lastname");
const venue = document.getElementById("venue");
const id = document.getElementById("id");
const select = document.getElementById("select");
const filterbtn = document.getElementById("filter");
const cancelbtn = document.getElementById("cButton");
let userLoged = JSON.parse(window.localStorage.getItem('user'));
var ncities;





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
    console.log(ncities)
    getVenuesByCity();
   
      
}
const getCityId = (vname)=>{  
  
    for(let i in ncities){
        console.log("ciudad 1: "+ncities[i].name);
        if(ncities[i].name == vname){
            
            return ncities[i].id;
        }       
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



const getVenueId = (vname)=>{  
  
    for(let i in venuesD){
        if(venuesD[i].name == vname){
            
            return venuesD[i].id;
        }       
    }

}



const deleteStaff = async (id)=>{
    
    //let obj = JSON.parse(json);

    let response = await fetch("http://localhost:8080/backend/api/users/"+id,
    {
        method: "DELETE",      
    }
);

if(response.ok){
    getAllUsers();
} 


        
}



const getAllUsers = async ()=>{

    let response = await fetch("http://localhost:8080/backend/api/users/null-null-null-null-3-"+userLoged.city);
    let data = await response.json();
    cardsC.innerHTML = "";
    
 
    for(let i in data){
        
        let user = data[i];  
        let sfView = new staffView(user);
        sfView.render(cardsC);  
        
    }

}



const getUserByParam = async()=>{

    let filterdata = checkFilter();
    console.log("http://localhost:8080/backend/api/users/"+filterdata.idO+"-"+filterdata.nameO+"-"+filterdata.lastnameO+"-"+filterdata.venueO+"-3-"+userLoged.city);
    let response = await fetch("http://localhost:8080/backend/api/users/"+filterdata.idO+"-"+filterdata.nameO+"-"+filterdata.lastnameO+"-"+filterdata.venueO+"-3-"+userLoged.city);
    let data = await response.json();
    cardsC.innerHTML = "";
    
    for(let i in data){
        let user = data[i];     
        let sfView = new staffView(user);
        sfView.render(cardsC);     
     
    }
}
const checkFilter = ()=>{

    let ln ="";
    let n = ""
    let vid = "";
    let nid = "";

      if(userN.value == ""){

         n = "null";

      }else{
        n = userN.value;
      }

      if(lastname.value ==""){
    
         ln = "null"; 
      }else{
         ln= lastname.value;
      }
      if(id.value == ""){

           nid = "null";
      }else{
          nid = id.value;
      }
 
    if(select.options[select.selectedIndex].text=="Seleccionar Sede"){
          vid = "null";
    }else{

        vid = getVenueId(select.options[select.selectedIndex].text);
    }

    let obj = {
           
           idO: nid,
           nameO : n,
           lastnameO: ln,
           venueO: vid
    };

    return obj
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
logoutBtn.addEventListener('click', ()=>{
    window.location.href = "index.html";
    userLoged = undefined;
  })
getAllUsers();
getAllCities();
getVenues();







