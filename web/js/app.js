/* iagocolodetti */

var app = angular.module('heroiApp', []);

app.controller('heroiController', function($scope, $http) {

    var webservice = "http://localhost:8080/Heroi/webresources/";
    
    $scope.carregarHerois = function() {
        $scope.heroisErro = null;
        $http.get(webservice + 'heroi/get')
                .then(function(response) {
                    $scope.herois = response.data;
                })
                .catch(function(error) {
                    $scope.heroisErro = "Erro: Não foi possível acessar o webservice para buscar os herois cadastrados.";
                });
    };
    
    $scope.carregarUniversos = function() {
        $http.get(webservice + 'universo/get')
                .then(function(response) {
                    $scope.universos = response.data;
                })
                .catch(function(error) {
                    alert('Erro: Não foi possível acessar o webservice para buscar os universos cadastrados.');
                });
    };
    
    $scope.poderes = [];
    
    $scope.adicionarPoder = function() {
        if ($scope.poder) {
            $scope.poderes.push({descricao: $scope.poder.trim()});
            $scope.poder = null;
        }
    };
   
    $scope.removerPoder = function(poder) {
        $scope.poderes.splice($scope.poderes.indexOf(poder), 1);
    };
    
    $scope.cadastrarHeroi = function() {
        var nome = $scope.nome.trim();
        var idUniverso = $scope.universo;
        if (nome) {
            if (idUniverso) {
                if ($scope.poderes.length > 0) {
                    $scope.cadastrarErro = null;
                    var heroi = { 'nome': nome, 'dataCadastro': new Date(), 'idUniverso': idUniverso, 'poderes': $scope.poderes };
                    try {
                        var xhr = new XMLHttpRequest();
                        xhr.open("POST", webservice + 'heroi/add', true);
                        xhr.setRequestHeader("Content-Type", "application/json");
                        xhr.send(angular.toJson(heroi));
                        $scope.resultado = "Herói '" + nome + "' foi adicionado com sucesso.";
                        $scope.nome = "";
                        $scope.poder = "";
                        $scope.poderes = [];
                    } catch (error) {
                        $scope.cadastrarErro = "Erro: Não foi possível adicionar o herói.";
                    }
                } else {
                    $scope.resultado = null;
                    $scope.cadastrarErro = "Erro: Adicione ao menos um poder.";
                }
            } else {
                $scope.resultado = null;
                $scope.cadastrarErro = "Erro: Selecione um universo.";
            }
        } else {
            $scope.resultado = null;
            $scope.cadastrarErro = "Erro: Digite um nome para o herói.";
        }
    };
    
    $scope.excluirHeroi = function(heroi) {
        if (confirm("A exclusão será feita no banco de dados e será permanente, deseja continuar?")) {
            try {
                var xhr = new XMLHttpRequest();
                xhr.open("PUT", webservice + 'heroi/delete/' + heroi.id, true);
                xhr.send(null);
                $scope.herois.splice($scope.herois.indexOf(heroi), 1);
                alert('Herói excluído com sucesso.');
            } catch (error) {
                alert('Erro: Não foi possível excluir o herói.');
            }
        }
    };
    
    $scope.propriedade = 'dataCadastro';
    $scope.reverso = false;
    $scope.ordenarPor = function(propriedade) {
        $scope.reverso = ($scope.propriedade === propriedade) ? !$scope.reverso : false;
        $scope.propriedade = propriedade;
    };
});
