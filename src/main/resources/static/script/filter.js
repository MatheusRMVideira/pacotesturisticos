function filtro(column, filterName){
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById(filterName);
    filter = input.value.toUpperCase();
    table = document.getElementById("tabela");
    tr = table.getElementsByTagName("tr");
    for (i = 2; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[column];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function filtroData(column) {
    var inputInicio, inputFim,filterInicio, table, tr, td, i, txtValue;
    inputInicio = document.getElementById("dataInicio");
    inputFim = document.getElementById("dataFim");
    filterInicio = inputInicio.value;
    filterInicio = filterInicio.split("-");
    filterFim = inputFim.value;
    filterFim = filterFim.split("-");

    if(filterInicio == ""){
        filterInicio[0] = "01";
        filterInicio[1] = "01";
        filterInicio[2] = "1900";
    }

    if(filterFim ==""){
        filterFim[0] = "2999";
        filterFim[1] = "12";
        filterFim[2] = "31";
    }

    table = document.getElementById("tabela");
    tr = table.getElementsByTagName("tr");
    for (i = 2; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[column];
        if (td) {
            txtValue = td.textContent || td.innerText;
            txtValue = txtValue.split("-");
            //Condicao mais feia que ja escrevi na minha vida
            if((txtValue[0] > filterInicio[0] || txtValue[0] == filterInicio[0] && txtValue[1] > filterInicio[1] || txtValue[0] == filterInicio[0] && txtValue[1] == filterInicio[1] && txtValue[2] >= filterInicio[2]) &&
            (txtValue[0] < filterFim[0] || txtValue[0] == filterFim[0] && txtValue[1] < filterFim[1] || txtValue[0] == filterFim[0] && txtValue[1] == filterFim[1] && txtValue[2] <= filterFim[2])){
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function filtroVigente(column) {
    var table, tr, td, i, txtValue;
    var dataSistema = new Date();
    var dia = dataSistema.getDate();
    var mes = dataSistema.getMonth() + 1;
    var ano = dataSistema.getFullYear();

    table = document.getElementById("tabela");
    tr = table.getElementsByTagName("tr");
    for (i = 2; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[column];
        if (td) {
            txtValue = td.textContent || td.innerText;
            txtValue = txtValue.split("-");
            //Condicao mais feia que ja escrevi na minha vida
            if(txtValue[0] > ano || txtValue[0] == ano && txtValue[1] > mes || txtValue[0] == ano && txtValue[1] == mes && txtValue[2] >= dia){
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}


function filtroNumero(column, filterName){
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById(filterName);
    filter = input.value;
    table = document.getElementById("tabela");
    tr = table.getElementsByTagName("tr");
    for (i = 2; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[column];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue == filter) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function limpar(){
    var table, tr, i;
    table = document.getElementById("tabela");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        tr[i].style.display = "";
    }


}

function changeAtivo(column){
    var table, tr, td, i, txtValue;
    table = document.getElementById("tabela");
    tr = table.getElementsByTagName("tr");
    for (i = 2; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[column];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue == "falso") {
                tr[i].addClass("cancelado");
            }
        }
    }
}

function compraPacote(pacoteId){
    window.location.href = "../compra/apresentacao?id=" + pacoteId;
}

$(function() {
    var imagesPreview = function(input, placeToInsertImagePreview) {
        if (input.files) {
            document.getElementsByClassName("preview")[0].innerHTML = "";
            var filesAmount = input.files.length;
            for (i = 0; i < filesAmount; i++) {
                var reader = new FileReader();
                reader.onload = function(event) {
                    $($.parseHTML("<img width='100' height='100'>")).attr('src', event.target.result).appendTo(placeToInsertImagePreview);
                }
                reader.readAsDataURL(input.files[i]);
            }
        }
    };
    $('#upload').on('change', function() {
        imagesPreview(this, 'div.preview');
    });
});

var apagadas = "";
function apagaImagem(button){
    button.parentElement.style.display = 'none';
    apagadas += button.id + ",";
    //append apagadas to hidden input field in form
    document.getElementById("apagadas").value = apagadas;
}
