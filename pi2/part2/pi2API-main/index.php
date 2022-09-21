<?php
require_once __DIR__ . '/vendor/autoload.php';
require_once __DIR__ . '/env.php';
require_once __DIR__ . '/src/slimConfiguration.php';
require_once __DIR__ . '/routes/index.php';
//require_once __DIR__ . '/Middlewares/auth.php';




//use Psr\Http\Message\ResponseInterface as Response;
// use Psr\Http\Message\ServerRequestInterface as Request;
// use Psr\Http\Server\RequestHandlerInterface as RequestHandler;
// use Slim\Psr7\Response;
// use Slim\Factory\AppFactory;


// $app = AppFactory::create();

// $app->addRoutingMiddleware();

// $errorMiddleware = $app->addErrorMiddleware(true, true, true);

// $mid1 = function(Request $request, RequestHandler $handler): Response {
//     $response = $handler->handle($request);
//     $existingContent = (string) $response->getBody();

//     $response = new Response();

//     $response->getBody()->write("dentro do mid1 <br>". $existingContent);

//     return $response;
// };

// $app->post("/produto", function( Request $request, Response $response, array $args) {
//     $data = $request->getParsedBody();
//     $nome = $data['nome'] ?? ' ';
//     $response->getBody()->write("Produto {$nome}");
//     return $response;
// });

// $app->get('/produtos', function(Request $request, Response $response, $args) {
//     $limit = $request->getQueryParams()['limit'] ?? 20;
//     $response->getBody()->write("{$limit} produtos do banco de dados");
//     return $response;
// })->add($mid1);