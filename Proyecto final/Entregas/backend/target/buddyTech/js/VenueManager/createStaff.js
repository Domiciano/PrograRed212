const id = document.getElementById("sId");
const nameTF = document.getElementById("sName");
const lastname = document.getElementById("sLastname");
const password = document.getElementById("sPass");
const venueId = document.getElementById("sVenueId");
const addBtn = document.getElementById("addBtn");
const cBtn = document.getElementById("cBtn");

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

    let response = await fetch("http://localhost:8080/backend/api/users/create", 
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




