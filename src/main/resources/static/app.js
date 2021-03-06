var app=angular.module('demo',
		['ngRoute','ngTouch','ui.bootstrap',
		'ngSanitize', 'angularUtils.directives.dirPagination',
		'ngLoadingSpinner', 'adaptv.adaptStrap', 'ui-notification',
		'chart.js', 'ngStomp', 'uiSwitch']);


app.constant('URL_API_BASE','http://localhost:8080/api/v1/');
app.constant('URL_BASE','http://localhost:8080/');



app.filter(
		'highlight',
		function($sce) {
			return function(text, phrase) {
				if (phrase && text) {
					text += '';
					text = text.replace(new RegExp('(' + phrase + ')', 'gi'),
							'<span style="background-color: yellow">$1</span>');
				}

				return $sce.trustAsHtml(text);
			}
		});

app.run(['$rootScope','$log','$location','$uibModal','coreService',function($rootScope,$log,$location,$uibModal,coreService){
	$log.log('Iniciando aplicación');
	$rootScope.titulo="Valor por defecto";
	$rootScope.relocate=function(loc){
		$location.path(loc); 
	};
	
	$rootScope.cleanLoginData = function() {
		$rootScope.autenticado = false;
		$rootScope.user = {
				name : "",
				mail: "",
				password : "",
				roles : []
			};
	};
	
	$rootScope.openLoginForm = function(size) {
		if (!$rootScope.loginOpen) {
			$rootScope.cleanLoginData();
			$rootScope.loginOpen = true;
			$uibModal.open({
				animation : true,
				backdrop : 'static',
				keyboard : false,
				templateUrl : 'views/loginForm.html',
				controller : 'LoginFormController',
				size : '40'
			});
		}
	};
	
	$rootScope.cbauth=false;
	$rootScope.authInfo=function(cb) {
		if(cb)
			$rootScope.cbauth=cb;
		coreService.authInfo().then(function(resp){
			if(resp.status===200) {
				$rootScope.user.name=resp.data.name;
				$rootScope.user.mail=resp.data.mail;
				$rootScope.user.roles = resp.data.roles;
				$rootScope.autenticado=true;
				if($rootScope.cbauth)
					$rootScope.cbauth();
			}else{
				$rootScope.cleanLoginData();
			}
		});
	}

	$rootScope.logout = function() {						
		coreService.logout().then(function(r){
			$rootScope.cleanLoginData();
		},function(){});
	};
	$rootScope.cleanLoginData();
}]);

app.controller('ctrl-div1',function($scope,$interval){
		$scope.titulo="Este es el título real";
		$scope.numero=Math.random();
		$scope.size=12;
		
		$scope.cantidad=0;
		
		$scope.intervalo=$interval(
			function(){
				$scope.cantidad++;
				$scope.numero=Math.random();
				if($scope.cantidad==3)
					$interval.cancel($scope.intervalo);
			}  
			,2000);
	}
);

app.controller('ctrl-div2',function($scope){
	
});


//controller    <--(área de memoria, se llama $scope)-->   vista(html) 