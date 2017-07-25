var app = angular.module('MINIAdminLoginApp', ['ngMaterial']);

app.config(function($mdThemingProvider) {
	/**
	 * Angular Material Themeing config
	 */
	$mdThemingProvider.theme('default').primaryPalette('blue-grey', {
		default: '900',
		'hue-1': '100',
		'hue-2': '300',
		'hue-3': '500'
	}).accentPalette('red', {
		default: '900',
		'hue-1': '100',
		'hue-2': '300',
		'hue-3': '500'
	});
});
