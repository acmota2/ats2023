module InputGenerator where

import Test.QuickCheck
import Control.Monad (sequence)
import Houses

main = do
    let fornecedores = concatMap ((++"\n") . show . Fornecedor) providers
    houses <- sequence $ replicate 200 (generate $ (arbitrary :: Gen House))
    writeFile "test.txt" $ fornecedores ++ (concatMap show houses)
    return ()
