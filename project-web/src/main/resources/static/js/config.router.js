'use strict';

/**
 * Config for the router
 */
angular.module('app')
  .run(
    [          '$rootScope', '$state', '$stateParams',
      function ($rootScope,   $state,   $stateParams) {
          $rootScope.$state = $state;
          $rootScope.$stateParams = $stateParams;        
      }
    ]
  )
  .config(
    [          '$stateProvider', '$urlRouterProvider',
      function (  $stateProvider,   $urlRouterProvider) {
          $urlRouterProvider
              .otherwise('/app/receiptMng');
          var provider = $stateProvider
              .state('app', {
                  abstract: true,
                  url: '/app',
                  templateUrl: 'tpl/app.html?v='+new Date().getTime()
              });
          provider.state('app.loanBillMng', {
                  url: '/loanBillMng',
                  templateUrl: 'pages/loanBillMng.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/loanBillMng.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.loanBill_edit', {
                  url: '/loanBill_edit',
                  templateUrl: 'pages/loanBill_edit.html',
                  params:{'id':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'pages/js/loanBill_edit.js?v='+new Date().getTime()
								]);
                               }
                           );
                       }]
                  }
              }).state('app.loanIncomeRecordMng', {
                  url: '/loanIncomeRecordMng',
                  templateUrl: 'pages/loanIncomeRecordMng.html',
                  params:{'loanBillId':''},
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/loanIncomeRecordMng.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.loanIncomeRecord_edit', {
                  url: '/loanIncomeRecord_edit',
                  templateUrl: 'pages/loanIncomeRecord_edit.html',
                  params:{'id':'', 'loanBillId':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'pages/js/loanIncomeRecord_edit.js?v='+new Date().getTime()
								]);
                               }
                           );
                       }]
                  }
              }).state('app.lenderMng', {
                  url: '/lenderMng',
                  templateUrl: 'pages/lenderMng.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/lenderMng.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.lender_edit', {
                  url: '/lender_edit',
                  templateUrl: 'pages/lender_edit.html',
                  params:{'id':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'pages/js/lender_edit.js?v='+new Date().getTime()
								]);
                               }
                           );
                       }]
                  }
              }).state('app.loanStatisicMng', {
                  url: '/loanStatisicMng',
                  templateUrl: 'pages/loanStatisicMng.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/loanStatisicMng.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.systemAccount', {
                  url: '/systemAccount',
                  templateUrl: 'pages/systemAccount.html',
                  resolve: {
                	  deps: ['$ocLazyLoad',
                             function( $ocLazyLoad){
                               return $ocLazyLoad.load(['tm.pagination', 'toaster']).then(
                                   function(){
                                      return $ocLazyLoad.load('pages/js/systemAccount.js?v='+new Date().getTime());
                                   }
                               );
                           }]
                  }
              }).state('app.systemAccount_edit', {
                  url: '/systemAccount_edit',
                  templateUrl: 'pages/systemAccount_edit.html',
                  params:{'id':''},
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'pages/js/systemAccount_edit.js?v='+new Date().getTime()
								]);
                               }
                           );
                       }]
                  }
              }).state('app.systemAccount_resetPwd', {
                  url: '/systemAccount_resetPwd',
                  templateUrl: 'pages/systemAccount_resetPwd.html',
                  resolve: {
                  	deps: ['$ocLazyLoad',
                         function( $ocLazyLoad){
                           return $ocLazyLoad.load('toaster').then(
                               function(){
                                  return $ocLazyLoad.load([
									'pages/js/systemAccount_resetPwd.js?v='+new Date().getTime()
								]);
                               }
                           );
                       }]
                  }
              })
      }
    ]
  );