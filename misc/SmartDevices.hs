module SmartDevices where

import Test.QuickCheck

resolutions = ["480x576", "1024x768", "1280x720", "1440x900", "1920x1080", "2560x1440", "3840x2160"]

brands = ["Philips", "Bose", "JBL", "Sony", "Boosey&Hawkes", "Bowers&Wilkins", "Alpine", "Marshall"]

radios = ["80s RFM", "Antena 1", "Antena 2", "Antena 3", "Antena Minho (Braga)", "Hiper FM", "Mega Hits FM", "Oceano Pacífico RFM", "Popular FM", "Radio 94 FM (Leiria)", "Radio Amalia (Lisboa)", "Radio Atlantida", "Radio Botareu (Agueda)", "Radio Cascais (Lisboa)", "Radio Cidade FM", "Radio Comercial", "Radio Festival", "Radio Marcoense (Marco De Canaveses)", "Radio Marginal", "Radio No Ar (Porto)", "Radio Nova", "Radio Nova Era (Vila Nova de Gaia)", "Radio Nove3cinco (Povoa De Lanhoso)", "Radio Orbital (Lisboa)", "Radio Radar (Lisboa)", "Radio RDP africa (Lisboa)", "Radio RFM", "Radio Santiago (Guimaraes)", "Radio Sao Miguel", "Radio TSF (Lisboa)", "Radio Universitaria do Minho (Braga)", "Radio Vizela", "RDP Internacional", "Renascença", "M80", "Rádio Meo Sudoeste (Lisboa)", "Onda Viva", "Rádio Oxigénio", "Rádio SBSR (Lisboa)", "Rádio Toca a Dançar", "Smooth FM (Lisboa)", "Super FM (Almada)", "Vodafone FM (Lisboa)"]

data LightLevel = Neutral | Warm | Cold deriving (Show, Eq)

instance Arbitrary LightLevel where
    arbitrary = elements [Cold, Neutral, Warm]

data SmartDevice = SmartBulb {
    intensity :: LightLevel,    -- self explanatory
    size :: Int                 -- de 8 a 20
} | SmartSpeaker {
    volume :: Int,              -- de 0 a 10
    station :: String,          -- da lista de radios
    brand :: String             -- da lista de marcas
} | SmartCamera {
    resolution :: String,       -- da lista de resoluções
    fileSize :: Int             -- de 8 a 256(MB)
} deriving (Eq)

instance Arbitrary SmartDevice where
    arbitrary = elements [genSmartBulb, genSmartSpeaker, genSmartCamera] >>= id

instance Show SmartDevice where
    show sd = case sd of
        (SmartBulb ll s) -> "SmartBulb:" ++ (show ll) ++ "," ++ (show s)
        (SmartCamera r fs) -> "SmartCamera:" ++ r ++ "," ++ (show fs)
        (SmartSpeaker v s b) -> "SmartSpeaker:" ++ (show v) ++ "," ++ s ++ "," ++ b

genSmartBulb = do
    intensity <- arbitrary :: Gen LightLevel
    size <- choose (8,20)
    return (SmartBulb intensity size)

genSmartSpeaker = do
    volume <- choose (0,10)
    station <- elements radios
    brand <- elements brands
    return (SmartSpeaker volume station brand)

genSmartCamera = do
    resolution <- elements resolutions
    fileSize <- choose (8,256)
    return (SmartCamera resolution fileSize)
