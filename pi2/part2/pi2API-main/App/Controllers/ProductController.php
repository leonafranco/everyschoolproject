<?php
namespace App\Controllers;

use App\DAO\Users;
use App\DAO\Lists;
use App\DAO\Items;
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Server\RequestHandlerInterface as RequestHandler;
use Slim\Psr7\Response;
use App\Models\listModel;
use App\Models\itemModel;
 
final class ProductController
 {

   public function debug_to_console($data) {
      $output = $data;
      if (is_array($output))

      echo "<script>console.log('Debug Objects: " . $output . "' );</script>";
  }

     public function getUser(Request $request, Response $response, array $args): Response
     {
        $userDAO = new Users();
        $users = $userDAO->getAllUsers();
        $response->getBody()->write(json_encode($users));
        return $response;
     }

     public function auth(Request $request, Response $response, array $args){
      $userDAO = new Users();
      $json = $request->getBody();
      $obj = json_decode($json);
      $auth = $userDAO->auth($obj->username, $obj->password);
      $response->getBody()->write(json_encode($auth));
      return $response;
     }

     public function createNewUser(Request $request, Response $response, array $args){
      $userDAO = new Users();
      $json = $request->getBody();
      $obj = json_decode($json);
      $result = $userDAO->createNewUser($obj->username, sha1($obj->password));
      $response->getBody()->write(json_encode($result));
      return $response;
     }

     public function updateUser(Request $request, Response $response, array $args){
      $json = $request->getBody();
      $obj = json_decode($json);
      var_dump($obj);
      $userDAO = new Users();
      $userDAO->updateUser($obj->username, $obj->param, $obj->newAtribute);
      return $response;
     }

     public function getLists(Request $request, Response $response, array $args){
      $username = $request->getQueryParams()['username'];
      $listsDAO = new Lists();
      $result = $listsDAO->getUserLists($username);
      $response->getBody()->write(json_encode($result));
      return $response;
     }

     public function createList(Request $request, Response $response, array $args){
      $json = $request->getBody();
      $obj = json_decode($json);
      $listsDAO = new Lists();
      $list = new listModel();
      $list->setName($obj->name);
      $result = $listsDAO->createNewList($list, $obj->username);
      $response->getBody()->write(json_encode($result));
      return $response;
     }

     public function updateList(Request $request, Response $response, array $args){
      $json = $request->getBody();
      $obj = json_decode($json);
      $listsDAO = new Lists();
      $listsDAO->updateList($obj->idList);
      return $response;
     }

     public function getItems(Request $request, Response $response, array $args){
      $listId = $request->getQueryParams()['id'];
      $itemDAO = new Items();
      $result = $itemDAO->getListItems($listId);
      $response->getBody()->write(json_encode($result));
      return $response;
     }

     public function createItems(Request $request, Response $response, array $args){
      $json = $request->getBody();
      $obj = json_decode($json);
      $itemDAO = new Items();
      $item = new itemModel();
      $item->setName($obj->name);
      $item->setComment($obj->comment);
      $item->setAmount($obj->amount);
      $itemDAO->createNewItem($item, $obj->idList);
      return $response;
     }

     public function deleteItem(Request $request, Response $response, array $args){
      $json = $request->getBody();
      $obj = json_decode($json);
      $itemDAO = new Items();
      $itemDAO->deleteItem($obj->idItem);
      return $response;
     }

     public function updateItem(Request $request, Response $response, array $args){
      $json = $request->getBody();
      $obj = json_decode($json);
      var_dump($obj);
      $itemDAO = new Items();
      $itemDAO->buyItem($obj->idItem, $obj->value);
      return $response;
     }
 }