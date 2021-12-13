const loginBtn = document.getElementById("loginBtn");
const userIdTF = document.getElementById("userIdTF");
const passTF = document.getElementById("passTF");

//const url = "http://192.168.1.54:8080/buddyTech_war/api/";
const url = "http://localhost:8080/backend/api/";

//let city = "";

const getCityName = async (id) => {

  let response = await fetch(url + "cty/" + id);
  if(response.ok){
    city = await response.text();
  return city;
  
  } 
};

const login = async () => {
  let req = {
    id: userIdTF.value,
    password: passTF.value,
  };
  let response = await fetch(url + "users/auth", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(req),
  });

  if (response.ok) {
    let data = await response.json();
    //console.log(data);

    //cambie 3 iguales por 2
    if (data === "no found") {
      // Alert Display
    } else {
        let city =  "all";
//Ojo falta un =
        if(data.roleBuddyID != 1){
            city = await getCityName(data.venuesBuddyID);
            console.log(city)
        }
      console.log(city);
      let user = {
        city,
        role: data.roleBuddyID,
        name: `${data.name} ${data.lastName}`,
      };

      console.log(user);
      
      
      window.localStorage.setItem('user', JSON.stringify(user));

      if(user.role == 1){
        window.location.href = "dashboardCEO.html";
      }else if(user.role == 2){
        window.location.href = "dashboardManager.html";
      }else if(user.role == 3){
        window.location.href = "dashboardStaff.html";
      }
      
    }
    
  }

  
};

loginBtn.addEventListener("click", () => {
  login();
});
