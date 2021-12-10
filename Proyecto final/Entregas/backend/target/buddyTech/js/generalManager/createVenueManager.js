const id = document.getElementById("idNum");
const nameTF = document.getElementById("name");
const lastname = document.getElementById("LastName");
const password = document.getElementById("pwd");
const addBtn = document.getElementById("createBtn");
const select = document.getElementById("venues");
var venuesD;
const postStaff = async ()=>{

    var str = select.options[select.selectedIndex].text;
    var venueId = getVenueId(str);
    let user = {

        id:id.value,
        name: nameTF.value,
        lastName:lastname.value,
        password:password.value,
        venuesBuddyID:venueId,
        roleBuddyID:2

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
        window.alert('Datos Actualizados: Creaste un nuevo subgerente');
        window.location.href='manageVenueManagers.html';
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




const getVenueId = (vname)=>{

    for(let i in venuesD){
        if(venuesD[i].name == vname){
            return venuesD[i].id;
        }
    }

}