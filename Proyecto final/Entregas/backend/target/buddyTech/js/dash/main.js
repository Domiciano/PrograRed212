import { firstChard, secondChard } from "../demo/chart-pie-demo.js";
import { chard } from "../demo/chart-area-demo.js";
import { months } from "./months.js";

// Select
const selectCity = document.getElementById("selectCity");

// Cards
const earningMonth = document.getElementById("earningMonth");
const earningYear = document.getElementById("earningYear");
const capacity = document.getElementById("capacity");
const occupationBar = document.getElementById("occupationBar");

// Pie Chards
let ctx1 = document.getElementById("myPieChart");
const ctx2 = document.getElementById("myPieChart2");

// Bars Age Chards
const ageBar1 = document.getElementById("ageBar1");
const ageBar2 = document.getElementById("ageBar2");
const ageBar3 = document.getElementById("ageBar3");
const ageBar4 = document.getElementById("ageBar4");
const ageBar5 = document.getElementById("ageBar5");

const progressbar1 = document.getElementById("progressbar1");
const progressbar2 = document.getElementById("progressbar2");
const progressbar3 = document.getElementById("progressbar3");
const progressbar4 = document.getElementById("progressbar4");
const progressbar5 = document.getElementById("progressbar5");

// Main Chard
let mainChard = document.getElementById("myAreaChart");

// Load User
const userName = document.getElementById("userName");
let userLoged = JSON.parse(window.localStorage.getItem('user'));


// URL
//const url = "http://192.168.1.54:8080/buddyTech_war/api/dash/";
const url = "http://localhost:8080/backend/api/dash/";

//logout
const logoutBtn = document.getElementById("logoutBtn");

const loadName = () => {
  userName.innerHTML = userLoged.name;
 console.log(userLoged);
}

const loadMainGraph = async () => {
  let response = await fetch(url + "monthly-earnings/" + selectCity.value);
  let data;

  months.forEach(mt => {
    mt.value = 0;
  });

  if (response.ok) {
    data = await response.json();

    //console.log(data)
    let startMonth = 0;

    if (data.length > 0) {
      for (let i = 0; i < data.length; i++) {

        let record = data[i];
        let recordDate = record.date.split("-");
        let found = false;

        for (let j = 0; j < months.length && !found; j++) {
          if (recordDate[1] == months[j].num) {
            months[j].value += record.amount;
            found = true;
          }
        }
      }

      startMonth = data[data.length - 1].date.split("-")[1] + 1;

      if (startMonth > 11) {
        startMonth = 0;
      }

    } else {
      let dateObj = new Date(Date.now());
      startMonth = dateObj.getMonth();
    }

    let orderedList = [];

    for (let i = 0; i < months.length; i++) {
      orderedList.push(months[startMonth]);
      startMonth++;
      if (startMonth > 11) {
        startMonth = 0;
      }
    }
    //console.log(orderedList)
    chard(orderedList, mainChard);
  }
};

const loadAgeSementation = async () => {
  let response = await fetch(url + "client-ages/" + selectCity.value);
  let data;
  let per = [];

  if (response.ok) {
    data = await response.json();
    let isEmpty = true;
    
    data.forEach(element => {
      if (element != 0) {
        isEmpty = false;
      }
    });
  
    if(!isEmpty){

      let sum = 0;
    for (let i = 0; i < data.length; i++) {
      sum += data[i];
    }

    per = data.map(function (num) {
      let value = (num * 100) / sum;
      return value + "%";
    })

    } else {
      per = ['0%','0%','0%','0%','0%','0%']
    }

    progressbar1.style.width = per[0];
    progressbar2.style.width = per[1];
    progressbar3.style.width = per[2];
    progressbar4.style.width = per[3];
    progressbar5.style.width = per[4];

    ageBar1.innerHTML = per[0];
    ageBar2.innerHTML = per[1];
    ageBar3.innerHTML = per[2];
    ageBar4.innerHTML = per[3];
    ageBar5.innerHTML = per[4];
  }

};

const loadClientStatus = async () => {
  let response1 = await fetch(url + "clientStatusData/" + selectCity.value);
  let data;

  if (response1.ok) {
    data = await response1.json();
  }

  let response2 = await fetch(url + "clientStatusLabels");
  let labels;
  if (response2.ok) {
    labels = await response2.json();
  }

  //console.log(data);

  firstChard(labels, data, ctx1);
};

const loadPlansStatus = async () => {
  let response1 = await fetch(url + "planStatusData");
  let data;
  if (response1.ok) {
    data = await response1.json();
  }
  let response2 = await fetch(url + "planStatusLabels");
  let labels;
  if (response2.ok) {
    labels = await response2.json();
  }
  secondChard(labels, data, ctx2);
};

const loadCards = async () => {
  let city = selectCity.value;

  let response1 = await fetch(url + `card/${city}/false`);
  let response2 = await fetch(url + `card/${city}/true`);
  let response3 = await fetch(url + `occupation/${city}`);

  if (response1.ok) {
    let earningM = await response1.json();
    earningMonth.innerHTML = earningM;
  }
  if (response2.ok) {
    let earningY = await response2.json();
    earningYear.innerHTML = earningY;
  }
  if (response3.ok) {
    let occupation = await response3.json();
    occupation = `${occupation}%`
    capacity.innerHTML = occupation;
    occupationBar.style.width = occupation;
  }
};

const loadData = () => {
  loadMainGraph();
  loadCards();
  loadClientStatus();
  loadPlansStatus();
  loadAgeSementation();
};

const loadSelectMenu = async () => {
  console.log(userLoged.city);
  let response = await fetch(url + "cities");
  if (response.ok) {
    let cities = await response.json();
    selectCity.innerHTML = '<option value="all" selected>Todas</option>';

    cities.forEach((city) => {
      let option = new CitySelect(city, userLoged.city);
      option.render(selectCity);
    });

    //Cambie dos iguales por 1
    if (userLoged.city !== "all") {
      selectCity.disabled = true;
    }
    loadData();
  }
};

logoutBtn.addEventListener('click', ()=>{
  window.location.href = "index.html";
  userLoged = undefined;
})

selectCity.addEventListener('change', () =>{
  
  document.getElementById("chartContainer1").innerHTML = '<canvas id="myAreaChart"></canvas>';
  document.getElementById("chartContainer2").innerHTML = '<canvas id="myPieChart"></canvas>';
  mainChard = document.getElementById("myAreaChart");
  ctx1 = document.getElementById("myPieChart");

loadData();
});

loadName();
loadSelectMenu();

