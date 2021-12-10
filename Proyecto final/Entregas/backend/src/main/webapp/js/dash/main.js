import { firstChard, secondChard } from "../demo/chart-pie-demo.js";

// Select
const selectCity = document.getElementById("selectCity");

// Cards
const earningMonth = document.getElementById("earningMonth");
const earningYear = document.getElementById("earningYear");
const capacity = document.getElementById("capacity");

// Pie Chards
const ctx1 = document.getElementById("myPieChart");
const ctx2 = document.getElementById("myPieChart2");

const url = "http://192.168.1.54:8080/buddyTech_war/api/dash/";

const loadMainGraph = async () => {};

const loadAgeSementation = async () => {
  let response = await fetch(url + "client-ages");
  let data;

  if (response.ok) {
    data = await response.json();
  }
  let labels = ["20 <", "20 a 30", "31 a 40", "41 a 50", "< 50"];

  let graphInfo = {
    data,
    labels,
  };

  return graphInfo;
};

const loadClientStatus = async () => {
  let response1 = await fetch(url + "clientStatusData");
  let data;

  if (response1.ok) {
    data = await response1.json();
  }

  let response2 = await fetch(url + "clientStatusLabels");
  let labels;
  if (response2.ok) {
    labels = await response2.json();
  }

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
    capacity.innerHTML = occupation + "%";
  }
};

const loadData = () => {
  loadCards();
  loadClientStatus();
  loadPlansStatus();
};

const loadSelectMenu = async () => {
  let response = await fetch(url + "cities");
  if (response.ok) {
    let cities = await response.json();
    selectCity.innerHTML = '<option value="all" selected>Todas</option>';

    cities.forEach((city) => {
      let option = new CitySelect(city);
      option.render(selectCity);
    });
    loadData();
  }
};

loadSelectMenu();
