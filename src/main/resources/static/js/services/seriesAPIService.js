angular.module("mySeriesList").service("seriesAPI",function($http){

	this.getSeriesAPI = function(nome){
		return $http.get('http://www.omdbapi.com/?s=' + nome + '&type=series&apikey=93330d3c');
	}

	this.getFullSeriesAPI = function(nome){
		return $http.get('https://omdbapi.com/?i=' + nome.imdbID + '&plot=full&apikey=93330d3c');
	}

});