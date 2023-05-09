module Houses where

import Test.QuickCheck
import SmartDevices

names = ["Abel", "Abilio", "Ana", "Anacleto", "Andre", "Antonio", "Beatriz", "Bernardete", "Bernardo", "Carolina", "Catarina", "Carlos", "Carlota", "Diana", "Diogo", "Dinis", "Dionisio", "Eva", "Eliseu", "Eusebio", "Fernando", "Galileu", "Hugo", "Joao", "Joaquim", "Jose", "Julio", "Luis", "Madalena", "Margarida", "Matilde", "Pedro", "Simao", "Simone", "Rita", "Rui", "Vasco", "Vera", "Viriato", "Zacarias", "Zelia"]

surnames = ["Albuquerque", "Antunes", "Araujo", "Barbosa", "Berardo", "Bettencourt", "Camoes", "Carvalho", "Cerqueira", "Coelho", "Costa", "Dias", "Fernandes", "Ferreira", "Henriques", "Leite", "Lino", "Lopes", "Machado", "Marinho", "Martins", "Mota", "Nunes", "Osorio", "Oliveira", "Pereira", "Pina", "Pinto", "Pinho", "Rebelo", "Reis", "Rodrigues", "Sa", "Santos", "Saramago", "Sequeira", "Sousa", "Vasques", "Vaz", "Velez", "Veloso"]

roomTypes = ["Quarto", "Cozinha", "Sala de estar", "Casa de banho", "Jardim", "Garagem", "Escritorio", "Sala de jantar"]

providers = ["EDP Comercial", "Galp Energia", "Iberdrola", "Endesa", "Gold Energy", "Coopernico", "Enat", "YIce", "MEO Energia", "Muon", "Luzboa", "Energia Simples", "SU Electricidade", "EDA"]

newtype Fornecedor = Fornecedor String deriving Eq

instance Show Fornecedor where
    show (Fornecedor f) = "Fornecedor:" ++ f

data House = House {
    fullname :: Name,
    nif :: Int,
    provider :: Fornecedor,
    rooms :: [Room]
} deriving (Eq)

instance Show House where
    show h = "Casa:" ++ (show $ fullname h) ++ "," ++ (show $ nif h) ++ "," ++ f ++ "\n" ++ (concatMap show $ rooms h)
        where (Fornecedor f) = provider h

newtype Name = Name String deriving Eq

instance Arbitrary Name where
    arbitrary = do
        name <- elements names
        surname1 <- elements surnames
        separator <- elements [" ", " de ", " e "]
        surname2 <- elements surnames
        return (Name (name ++ " " ++ surname1 ++ separator ++ surname2))

instance Show Name where
    show (Name fullname) = fullname

instance Arbitrary House where
    arbitrary = do
        fullname <- arbitrary
        nif <- choose (100000000, 290000000)
        roomN <- choose (4, length roomTypes)
        providerN <- elements providers
        rooms <- listOf1 arbitrary -- isto está bastante problemático
        return (House fullname nif (Fornecedor providerN) rooms)

data Room = Room {
    name :: RoomName,
    smartDevices :: [SmartDevice]
}

instance Arbitrary Room where
    arbitrary = do
        rname <- elements roomTypes
        smartDevices <- listOf1 arbitrary
        return (Room (RoomName rname) smartDevices)

instance Show Room where
    show r = "Divisao:" ++ (show $ name r) ++ "\n" ++ (concatMap ((++"\n") . show) i) ++ (show l) -- isto é porco de tantas maneiras
        where (i, l) = (init $ smartDevices r, last $ smartDevices r)

newtype RoomName = RoomName String deriving Eq

instance Show RoomName where
    show (RoomName r) = r

instance Eq Room where
    r1 == r2 = (name r1) == (name r2)
