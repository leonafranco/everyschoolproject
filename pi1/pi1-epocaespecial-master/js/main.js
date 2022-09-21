document.getElementById("formulario").addEventListener('submit', inserir_animal);
document.getElementById("adicionar_alimento").addEventListener('submit', inserir_alimento);
document.getElementById("adicionar_agua").addEventListener('submit', inserir_agua);
document.getElementById("adicionar_vet").addEventListener('submit', inserir_vet);


function inserir_animal(e) {
    var nome = document.getElementById('nome').value;
    var genero = document.getElementById('genero').value;
    var especie = document.getElementById('especie').value;
    var data_nasc = document.getElementById('data_nasc').value;
    var raca = document.getElementById('raca').value;

    if(nome== "" || genero=="" || especie == "" || data_nasc=="" || raca=="") {
        alert("Por favor insira um animal com dados não vazios!");
        return false;
    }
    
    animal = {
        nome_animal: nome,
        genero_animal: genero,
        especie_animal: especie,
        data_nasc_animal: data_nasc,
        raca_animal: raca,
    }


    if (localStorage.getItem('novo_animal') == null) {
        var animais = [];
        animais.push(animal);
        localStorage.setItem('novo_animal', JSON.stringify(animais));
    }else {
        var animais = JSON.parse(localStorage.getItem('novo_animal'));
        animais.push(animal);
        localStorage.setItem('novo_animal', JSON.stringify(animais));
    }




    document.getElementById("formulario").reset();

    listar_seletor();
    listar_animal();

    e.preventDefault();
}

function inserir_alimento(e) {
    var nome = document.getElementById('nome_comida').value;
    var data = document.getElementById('data_comida').value;
    var hora = document.getElementById('hora_comida').value;
    var quantidade = document.getElementById('quantidade_comida').value;
    var tipo = document.getElementById('tipo_comida').value;

    if(nome== "" || data == "" || hora == "" || quantidade=="" || tipo=="") {
        alert("Por favor insira uma comida com dados não vazios!");
        return false;
    }
    
    comida = {
        nome_comida: nome,
        data_comida: data,
        hora_comida: hora,
        quantidade_comida: quantidade,
        tipo_comida: tipo,
    }


    if (localStorage.getItem('novo_comida') == null) {
        var comidas = [];
        comidas.push(comida);
        localStorage.setItem('novo_comida', JSON.stringify(comidas));
    }else {
        var comidas = JSON.parse(localStorage.getItem('novo_comida'));
        comidas.push(comida);
        localStorage.setItem('novo_comida', JSON.stringify(comidas));
    }




    document.getElementById("adicionar_alimento").reset();




    e.preventDefault();
}

function inserir_agua(e) {
    var nome = document.getElementById('nome_agua').value;
    var data = document.getElementById('data_agua').value;
    var hora = document.getElementById('hora_agua').value;


    if(nome== "" || data == "" || hora =="") {
        alert("Por favor insira uma mudança de agua com dados não vazios!");
        return false;
    }
    
    agua = {
        nome_agua: nome,
        data_agua: data,
        hora_agua: hora,
    }


    if (localStorage.getItem('novo_agua') == null) {
        var aguas = [];
        aguas.push(agua);
        localStorage.setItem('novo_agua', JSON.stringify(aguas));
    }else {
        var aguas = JSON.parse(localStorage.getItem('novo_agua'));
        aguas.push(agua);
        localStorage.setItem('novo_agua', JSON.stringify(aguas));
    }




    document.getElementById("adicionar_agua").reset();


    listar_animal();

    e.preventDefault();
}

function inserir_vet(e) {
    var nome = document.getElementById('nome_vet').value;
    var data = document.getElementById('data_vet').value;
    var hora = document.getElementById('hora_vet').value;
    var motivo = document.getElementById('motivo_vet').value;


    if(nome== "" || data == "" || hora == "" || motivo=="") {
        alert("Por favor insira uma ida ao veterinario com dados não vazios!");
        return false;
    }
    
    veterinario = {
        nome_vet: nome,
        data_vet: data,
        hora_vet: hora,
        motivo_vet: motivo
    }


    if (localStorage.getItem('novo_vet') == null) {
        var veterinarios = [];
        veterinarios.push(veterinario);
        localStorage.setItem('novo_vet', JSON.stringify(veterinarios));
    }else {
        var veterinarios = JSON.parse(localStorage.getItem('novo_vet'));
        veterinarios.push(veterinario);
        localStorage.setItem('novo_vet', JSON.stringify(veterinarios));
    }




    document.getElementById("adicionar_vet").reset();


    listar_animal();

    e.preventDefault();
}

function apagar_animal(nome) {
    var animais = JSON.parse(localStorage.getItem('novo_animal'));

    for(let i = 0; i < animais.length; i++) {
        if(animais[i].nome_animal == nome) {
            animais.splice(i, 1);
        }

        localStorage.setItem('novo_animal', JSON.stringify(animais))
    }
    
    listar_seletor();
    listar_animal();
}

//! ha aqui um bugzito criminoso, ele lista 2x os nomes. não deixar listar o seletor
function listar_seletor() {
    var animais = JSON.parse(localStorage.getItem('novo_animal'));


    for(let i = 0; i < animais.length; i++) {

        var y = document.getElementById("nome_comida");


        //y.innerHTML = ''; pode ser que dê


        var option = document.createElement("option");
        option.text = animais[i].nome_animal;
        y.add(option);

    }

    for(let i = 0; i < animais.length; i++) {
        var x = document.getElementById("nome_agua");

        var option = document.createElement("option");
        option.text = animais[i].nome_animal;
        x.add(option);
    }

    for(let i = 0; i < animais.length; i++) {
        var z = document.getElementById("nome_vet");

        var option = document.createElement("option");
        option.text = animais[i].nome_animal;

        z.add(option);
    }
}

function listar_animal() {
    var animais = JSON.parse(localStorage.getItem('novo_animal'));
    var animais_resultado = document.getElementById('resultados');


    animais_resultado.innerHTML = '';

    for (let i = 0; i < animais.length; i++) {
        var nome = animais[i].nome_animal;
        var genero = animais[i].genero_animal;
        var especie = animais[i].especie_animal;
        var data_nasc = animais[i].data_nasc_animal;
        var raca_animal = animais[i].raca_animal;

        animais_resultado.innerHTML += '<tr><td>' + nome + 
        '</td><td>' + genero +
        '</td><td>' + especie + 
        '</td><td>' + data_nasc + 
        '</td><td>' + raca_animal +
        '</td><td>' + '<button onclick = "apagar_animal(\''+ nome +'\')">Excluir' +
        '</td>';
    }


}

function pesquisar() {

    var pesq = document.getElementById('pesquisartudinho').value.split("-");
    var animais = JSON.parse(localStorage.getItem('novo_comida'));

    var comida_resultado = document.getElementById('resultados_comida');
    var agua_resultado = document.getElementById('resultados_agua');
    var vet_resultado = document.getElementById('resultados_vet');

    comida_resultado.innerHTML = '';

    for(var i = 0; i < animais.length; i++) {
        let temp = animais[i].data_comida.split("-")
        if(pesq[1] == temp[1]) {
            var nome = animais[i].nome_comida;
            var data = animais[i].data_comida;
            var hora = animais[i].hora_comida;
            var quantidade = animais[i].quantidade_comida;
            var tipo = animais[i].tipo_comida;

            comida_resultado.innerHTML += '<tr><td>' + nome + '</td><td>' + data + '</td><td>' + hora +'</td><td>' + quantidade + '</td><td>' + tipo + '</td>';
        }
    }

    agua_resultado.innerHTML = '';


    animais = JSON.parse(localStorage.getItem('novo_agua'));
    
    for(var i = 0; i < animais.length; i++) {
        let temp = animais[i].data_agua.split("-")
        if(pesq[1] == temp[1]) {
            var nome = animais[i].nome_agua;
            var data = animais[i].data_agua;
            var hora = animais[i].hora_agua;

            agua_resultado.innerHTML += '<tr><td>' + nome + '</td><td>' + data + '</td><td>' + hora +'</td>';
        }
    }

    vet_resultado.innerHTML = '';

    animais = JSON.parse(localStorage.getItem('novo_vet'));
    
    for(var i = 0; i < animais.length; i++) {
        let temp = animais[i].data_vet.split("-")
        if(pesq[1] == temp[1]) {
            var nome = animais[i].nome_vet;
            var data = animais[i].data_vet;
            var hora = animais[i].hora_vet;
            var motivo = animais[i].motivo_vet;

            vet_resultado.innerHTML += '<tr><td>' + nome + '</td><td>' + data + '</td><td>' + hora+ '</td><td>' + motivo + '</td><td>' + '</td>';
        }
    }

}