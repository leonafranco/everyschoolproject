<?php

// use Psr\Http\Message\ResponseInterface as Response;

use App\Controllers\ProductController;
use Slim\Factory\AppFactory;

use function src\slimConfiguration;

 $app = AppFactory::create();

 slimConfiguration($app);

 $app->options('/{routes:.+}', function ($request, $response, $args) {
    return $response;
});

$app->add(function ($request, $handler) {
    $response = $handler->handle($request);
    return $response
        ->withHeader('Access-Control-Allow-Origin', '*')
        ->withHeader('Access-Control-Allow-Headers', 'X-Requested-With, Content-Type, Accept, Origin, Authorization')
        ->withHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, PATCH, OPTIONS');
});

 $app->post('/auth', ProductController::class . ':auth');
 $app->post('/createNewUser', ProductController::class . ':createNewUser');
 $app->put('/updateUser', ProductController::class . ':updateUser');
 $app->delete('/', ProductController::class . ':deleteUser');


 $app->get('/lists', ProductController::class . ':getLists');
 $app->post('/createList', ProductController::class . ':createList');
 $app->put('/updateList', ProductController::class . ':updateList');

 $app->get('/items', ProductController::class . ':getItems');
 $app->post('/buyItem', ProductController::class . ':createItems');
 $app->put('/deleteItem', ProductController::class . ':deleteItem');
 $app->put('/updateItem', ProductController::class . ':updateItem');

//  $app->post('/createItem', ProductController::class . ':createItem');
//  $app->put('/updateList', ProductController::class . ':updateItem');
 


 $app->run();