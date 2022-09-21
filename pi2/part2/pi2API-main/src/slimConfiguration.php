<?php

namespace src;

function slimConfiguration($app) {
    $errorMiddleware = $app->addErrorMiddleware(true, true, true);
    return $errorMiddleware;
}