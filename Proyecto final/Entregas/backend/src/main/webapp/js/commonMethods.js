//Al método le ingresa una fecha del atributo de endDate del cliente y retorna los días remanentes a vencerse
 const daysLeft = (memEndDate)=>{

    let userEndDate = new Date(memEndDate);
    let today = new Date();

    let difference = Math.abs(userEndDate-today);
    let days = parseInt((difference/(1000 * 3600 * 24)));
    console.log("days left: "+days);

    return days;
}

//Al metodo le ingresa una fecha de expiración dada por el cliente y saca un boolean notificando si puede acceder o no
const evaluateDateAccess = (memEndDate) =>{
    let userEndDate = new Date(memEndDate);
    let today = new Date();

    console.log("hoy "+today);
    console.log("Mem "+userEndDate);
return (userEndDate > today);

    }


