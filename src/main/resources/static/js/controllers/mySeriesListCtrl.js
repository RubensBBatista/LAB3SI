angular.module("mySeriesList").controller("mySeriesListCtrl",function($scope,$http,seriesAPI){
	
	$scope.series = [];
	$scope.mySeries = [];
	$scope.watchlist = [];
	// variavel utilizada para saber se alguma serie foi pesquisada
	$scope.searchState = "";
	$scope.clientes = [];

	$scope.getSeries = function(nome){
		var promise = seriesAPI.getSeriesAPI(nome).then(function(response){
			$scope.series = response.data.Search;
			$scope.searchState = response.data.Error;
		}, function error(error){
			console.log(error);
		})
		return promise; 
	};

	$scope.addSerie = function(nomeserie){
		if(!($scope.mySeriesContains(nomeserie))){
			var promise = seriesAPI.getFullSeriesAPI(nomeserie).then(function(response){
     			$scope.mySeries.push(response.data);
     		}).catch(function(error){
     			console.log(error);
     		});
		}else{
			alert("Não é possivel adicionar séries que você já possui no seu perfil!");
		};
		if(_.includes($scope.watchlist,nomeserie)){
			var pos = $scope.watchlist.indexOf(nomeserie);
			$scope.watchlist.splice(pos,1);
		};
		
	};

	$scope.mySerieRemove = function(serie){
		var confirmacao = confirm("Tem certeza que deseja remover " + serie.Title + " do seu perfil?");
		if(confirmacao){
			var pos = $scope.mySeries.indexOf(serie);
			$scope.mySeries.splice(pos,1);
		};
	};

	$scope.watchlistAdd = function(nomeserie){
		if($scope.mySeriesContains(nomeserie)){
			alert("Você não pode adicionar essa série na sua watchlist pois ela já está no seu perfil");
		}else{
			if(!(_.includes($scope.watchlist,nomeserie))){
				$scope.watchlist.push(nomeserie);
			}else{
				alert("Essa série já esta na sua Watchlist!");
			};
		};
		
	};

	$scope.watchlistRemove = function(serie){
		var pos = $scope.watchlist.indexOf(serie);
		$scope.watchlist.splice(pos,1);
	};

	$scope.pesquisado = function(){
		return $scope.searchState === "Movie not found!";
	};

	$scope.mySeriesContains = function(serie){
    	for (var i = 0; i < $scope.mySeries.length; i++) {
     		if ($scope.mySeries[i].imdbID === serie.imdbID){
        		return true;
       		}
    	}
    	return false;
  	};

  	$scope.setMyRating = function(serie,nota){
  		serie.myRating = nota; 		
  	};

  	$scope.setLastEpisode = function(serie,ep){
  		serie.lastEpisode = ep;
  	};
  	
  	var getClientes = function(){
  		$http({
  	  	  method: 'GET',
  	  	  url: 'http://localhost:8080/clientes'
  	  	}).then(function successCallback(response) {
  	  		$scope.clientes = response.data;
  	  		console.log(response.data);
  	  	  }, function errorCallback(response) {
  	  		console.log("Deu erro");
  	  	  });
  	};
  	
  	$scope.cadastraCliente = function(idNome,idLogin,idSenha){
  		$http({
  	  	  method: 'POST',
  	  	  url: 'http://localhost:8080/clientes',
  	  	  data: { nome : idNome, login : idLogin , password : idSenha}	
  	  	}).then(function successCallback(response) {
  	  		console.log(response.data);
  	  	  }, function errorCallback(response) {
  	  		 console.log("Deu erro");
  	  	  });
  	};
  	
  	getClientes();
  	
  	
  	

});