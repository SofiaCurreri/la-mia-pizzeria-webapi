/*CONSTANTS*/
const PIZZA_API_URL = "http://localhost:8080/api/v1/pizzas";
const contentDOM = document.getElementById("content");
const deletedMessage = document.getElementById("deleted-message");

/*API*/
//TUTTE LE PIZZE
const getPizzas = async () => {
  try {
    const response = await axios.get(PIZZA_API_URL);
    console.log(response);
    return response.data;
  } catch (error) {
    console.log(error);
  }
};

//ELIMINO PIZZA al click sul cestino
const deletePizza = async (event) => {
  event.preventDefault();
  const pizzaId = event.target.closest(".delete-pizza").dataset.id;

  try {
    //trovo la pizza da eliminare, cosi da poter poi accedere al suo nome e usarlo in deletedMessage
    const data = await getPizzas();
    const deletedPizza = data.find((pizza) => pizza.id === parseInt(pizzaId));

    //chiamata API per eliminare la pizza
    const response = await axios.delete(`${PIZZA_API_URL}/${pizzaId}`);
    console.log(response);

    //messaggio di eliminazione avvenuta
    deletedMessage.innerHTML = `<div class= "alert alert-danger mt-4 w-100"> Pizza ${deletedPizza.name} was deleted! </div>`;

    //ricarico i dati dopo l'eliminazione della pizza
    loadData();
  } catch (error) {
    console.log(error);
  }
};

/*DOM MANIPULATION*/
//LISTA PIZZE con dati presi in ingresso
const createPizzaList = (data) => {
  if (data.length > 0) {
    //parte iniziale "fissa" della struttura della lista
    let list = `<table class="table mt-4">
    <thead>
    <tr>
        <th scope="col">Pizzas </i></th>
        <th scope="col">Ingredients</th>
        <th scope="col">Price</th>
        <th scope="col" class="text-center">Actions</th>
    </tr>
    </thead>

    <tbody>`;

    //itero lista, parte variabile lista
    data.forEach((element) => {
      list += `<tr>
      <td class="align-middle">${element.name}</td>
      <td class="align-middle">${element.description}</td>
      <td class="align-middle">&euro; ${element.price}</td>
      <td class="align-middle text-center">
        <form method="delete" class="delete-pizza" data-id="${element.id}">
            <button type="submit" class="btn text-danger"><i class="fa-solid fa-trash-can"></i></button>
        </form>         
      </td>
    </tr>`;
    });

    //parte finale fissa della lista
    list += `</tbody>
    </table>`;

    return list;
  } else {
    return `<div class= "alert alert-info mt-4"> The list is empty, sorry :( </div>`;
  }
};

//MOSTRO PIZZE
const loadData = async () => {
  //prendo dati dall' api
  const data = await getPizzas();
  //è una semplice stringa, devo "appenderla" all' html
  //createPizzaList(data);
  //qui l' appendo all' html
  contentDOM.innerHTML = createPizzaList(data);
};

//FILTRO PIZZE in base alla keyword di ricerca
const filterPizzas = (data, keyword) => {
  if (!keyword) {
    // Se la keyword è vuota, mostra tutte le pizze
    return data;
  }
  //altrimenti ...
  keyword = keyword.toLowerCase();

  return data.filter(
    (element) =>
      element.name.toLowerCase().includes(keyword) ||
      element.description.toLowerCase().includes(keyword)
  );
};

// Funzione per gestire l'evento di submit del form di ricerca
const searchFormSubmit = async (event) => {
  // Impedisce l'invio del form tradizionale
  event.preventDefault();

  const keyword = event.target.elements.keyword.value;
  const data = await getPizzas();
  const filteredData = filterPizzas(data, keyword);
  contentDOM.innerHTML = createPizzaList(filteredData);
};

//aggiungo l'evento di submit del form alla funzione di gestione
const searchForm = document.querySelector("form");
searchForm.addEventListener("submit", searchFormSubmit);

//aggiungo l'evento di click per il pulsante del cestino
contentDOM.addEventListener("click", (event) => {
  if (event.target.classList.contains("fa-trash-can")) {
    deletePizza(event);
  }
});

/*CODICE GLOBALE*/
loadData();
