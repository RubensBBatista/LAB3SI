angular.module("mySeriesList").controller("mySeriesListCtrl",function($scope,$http,seriesAPI){
	
	$scope.series = [];
	$scope.searchState = "";
	$scope.clientes = [];
	$scope.userLogado;
	$scope.mySeries = [];
	$scope.watchlist = [];
	
	//para resetar o modal
	
	$('#modalCadastro').on('hidden.bs.modal', function(){
		$(this).find('form').trigger('reset');
		$('#modalLogin').find('form').trigger('reset');

		});
	
	$('#modalLogin').on('hidden.bs.modal', function(){
		$(this).find('form').trigger('reset');
		$('#modalCadastro').find('form').trigger('reset');

		});

	$scope.getSeries = function(nome){
		var promise = seriesAPI.getSeriesAPI(nome).then(function(response){
			$scope.series = response.data.Search;
			$scope.searchState = response.data.Error;
		}, function error(error){
			console.log(error);
		})
		return promise; 
	};
	
	$scope.addSerie = function(serie){
		if(!$scope.hasLogado()){
			alert("Você precisa estar logado para adicionar séries ao seu perfil!");
		}else{
			$scope.addToPerfil(serie);
		}
	}
	
	$scope.addToPerfil = function(serie){
		nomeserie = $scope.convertToAcceptAtt(serie);
		if(contains($scope.mySeries,nomeserie) == -1){
			var promise = seriesAPI.getFullSeriesAPI(nomeserie).then(function(response){
				var descricao = (response.data.Plot).substring(0,220) + "...";
				var serieadd = $scope.convertToAcceptAtt(response.data);
				serieadd.plot = descricao;
     			$scope.mySeries.push(serieadd);
     			$scope.salvarNoPerfil(serieadd);
     		}).catch(function(error){
     			console.log(error);
     		});
		}else{
			alert("Não é possivel adicionar séries que você já possui no seu perfil!");
		};
		index_contains = contains($scope.watchlist,nomeserie);
		if(index_contains != -1){
			$scope.watchlist.splice(index_contains,1);
			$scope.removerDaWatchlist(nomeserie);
		};
		
	};

	$scope.mySerieRemove = function(serie){
		var confirmacao = confirm("Tem certeza que deseja remover " + serie.title + " do seu perfil?");
		if(confirmacao){
			var pos = $scope.mySeries.indexOf(serie);
			$scope.mySeries.splice(pos,1);
			$scope.removerDoPerfil(serie);
		};
	};
	
	$scope.watchlistAdd = function(serie){
		if(!$scope.hasLogado()){
			alert("Você precisa estar logado para adicionar séries a sua watchlist!");
		}else{
			$scope.addToWatchlist(serie);
		}
	}

	$scope.addToWatchlist = function(serie){
		nomeserie = $scope.convertToAcceptAtt(serie);
		if(contains($scope.mySeries,nomeserie) != -1){
			alert("Você não pode adicionar essa série na sua watchlist pois ela já está no seu perfil");
		}else{
			if(contains($scope.watchlist,nomeserie) == -1){
				$scope.watchlist.push(nomeserie);
				$scope.salvarNaWatchList(nomeserie);
			}else{
				alert("Essa série já esta na sua Watchlist!");
			};
		};
		
	};

	$scope.pesquisado = function(){
		return $scope.searchState === "Movie not found!";
	};

	var contains = function(array,nomeserie){
		if(array == undefined) return false;
    	for (var i = 0; i < array.length; i++) {
     		if (array[i].imdbID === nomeserie.imdbID){
        		return i;
       		}
    	}
    	return -1;
  	};

  	$scope.setMyRating = function(serie,nota){
  		serie.myRating = nota;
  		$scope.salvarNoPerfil(serie);
  	};

  	$scope.setLastEpisode = function(serie,ep){
  		serie.lastEpisode = ep;
  		$scope.salvarNoPerfil(serie);
  	};
  	
  	$scope.hasLogado = function(){
  		return $scope.userLogado != null;
  	};
  	
  	$scope.deslogar = function(){
  		$scope.userLogado = null;
  		$scope.mySeries = [];
  		$scope.watchlist = [];
  	};
  	
 	$scope.hasSeriesOnPerf = function(){
  		return $scope.mySeries.length > 0;
  	}
  	
  	$scope.hasSeriesOnWatch = function(){
  		return $scope.watchlist.length > 0;
  	}
  	
  	$scope.autenticarCliente = function(idLogin,idSenha){
  		$http({
    	  	  method: 'POST',
    	  	  url: 'http://localhost:8080/clientes/autenticar',
    	  	  data: { login : idLogin , password : idSenha}	
    	  	}).then(function successCallback(response) {
    	  		if(response.data.nome == null){
    	  			alert("Email ou senha incorretos");
    	  		}else{
    	  			$scope.userLogado = response.data;
    	  			$scope.fillSeries();
    	  			alert("Bem vindo " + response.data.nome + " :D");
    	  		}
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
  	  		$scope.autenticarCliente(idLogin,idSenha);
  	  		alert("Cliente cadastrado com sucesso");
  	  	  }, function errorCallback(response) {
  	  		 console.log("Deu erro");
  	  	  });
  	};
  	
  	$scope.salvarNoPerfil = function(serie){
  		$http({
    	  	  method: 'POST',
    	  	  url: 'http://localhost:8080/cliente/perfil/' + $scope.userLogado.id,
    	  	  data: serie
    	  	}).then(function successCallback(response) {
    	  	  }, function errorCallback(response) {
    	  		 console.log("Deu erro no perfil");
    	  	  });
  	}
  	
  	$scope.salvarNaWatchList = function(serie){
  		$http({
  	  	  method: 'POST',
  	  	  url: 'http://localhost:8080/cliente/watchlist/' + $scope.userLogado.id,
  	  	  data: serie
  	  	}).then(function successCallback(response) {
  	  	  }, function errorCallback(response) {
  	  		 console.log("Deu erro na watchlist");
  	  	  });

  	}
  	
  	$scope.fillSeries = function(){
  		if($scope.userLogado.meuPerfil != undefined){
  			$scope.mySeries = angular.copy($scope.userLogado.meuPerfil);
  		}
  		if($scope.userLogado.watchlist != undefined){
  			$scope.watchlist = angular.copy($scope.userLogado.watchlist);
  		}
  	}
  	
  	$scope.removerDoPerfil = function(serie){
  		$http({
  	  	  method: 'DELETE',
  	  	  url: 'http://localhost:8080/cliente/removerPerfil/' 
  	  		  + $scope.userLogado.id + "/" + serie.imdbID,
  	  	}).then(function successCallback(response) {
  	  	  }, function errorCallback(response) {
  	  		 console.log("Deu erro na remocao do perfil");
  	  	  });

  	}
  	
  	$scope.removerDaWatchlist = function(serie){
  		$http({
  	  	  method: 'DELETE',
  	  	  url: 'http://localhost:8080/cliente/removerWatchList/' 
  	  		  + $scope.userLogado.id + "/" + serie.imdbID,
  	  	}).then(function successCallback(response) {
  	  	  }, function errorCallback(response) {
  	  		 console.log("Deu erro na remocao do perfil");
  	  	  });

  	}
 	
  	$scope.convertToAcceptAtt = function(serie){
  		var retorno = {
                 imdbRating: serie.imdbRating,
                 title: serie.Title,
                 rated: serie.Rated,
                 poster: serie.Poster,
                 imdbID: serie.imdbID
  		};
  		return retorno;
  	}
  	

});