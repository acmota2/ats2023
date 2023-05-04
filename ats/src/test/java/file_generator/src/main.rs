use std::collections::HashSet;
use std::env;
use std::fs::File;
use std::io::{BufWriter, Write};
use rand::{Rng, thread_rng, prelude::SliceRandom, prelude::ThreadRng};

static FORNECEDORES: &[&str] = &[
    "EDP Comercial", "Galp Energia", "Iberdrola", "Endesa", "Gold Energy", "Coopernico", "Enat", "YIce", "MEO Energia", "Muon", "Luzboa", "Energia Simples", "SU Electricidade", "EDA"
];

static NOMES: &[&str] = &[
    "Abel", "Abilio", "Ana", "Anacleto", "Andre", "Antonio", "Beatriz", "Bernardete", "Bernardo", "Carolina", "Catarina", "Carlos", "Carlota", "Diana", "Diogo", "Dinis", "Dionisio", "Eva", "Eliseu", "Eusebio", "Fernando", "Galileu", "Hugo", "Joao", "Joaquim", "Jose", "Julio", "Luis", "Madalena", "Margarida", "Matilde", "Pedro", "Simao", "Simone", "Rita", "Rui", "Vasco", "Vera", "Viriato", "Zacarias", "Zelia"
];

static APELIDOS: &[&str] = &[
    "Albuquerque", "Antunes", "Araujo", "Barbosa", "Berardo", "Bettencourt", "Camoes", "Carvalho", "Cerqueira", "Coelho", "Costa", "Dias", "Fernandes", "Ferreira", "Henriques", "Leite", "Lino", "Lopes", "Machado", "Marinho", "Martins", "Mota", "Nunes", "Osorio", "Oliveira", "Pereira", "Pina", "Pinto", "Pinho", "Rebelo", "Reis", "Rodrigues", "Sa", "Santos", "Saramago", "Sequeira", "Sousa", "Vasques", "Vaz", "Velez", "Veloso"
];

static RESOLUCOES: &[&str] = &[
    "480x576", "1024x768", "1280x720", "1440x900", "1920x1080", "2560x1440", "3840x2160"
];

static LIGHTS: &[&str] = &[
    "Neutral", "Warm", "Cold"
];

static MARCAS: &[&str] = &[
    "Philips", "Bose", "JBL", "Sony", "Boosey&Hawkes", "Bowers&Wilkins", "Alpine", "Marshall"
];

static RADIOS: &[&str] = &[
    "80s RFM", "Antena 1", "Antena 2", "Antena 3", "Antena Minho (Braga)", "Hiper FM", "Mega Hits FM", "Oceano Pacífico RFM", "Popular FM", "Radio 94 FM (Leiria)", "Radio Amalia (Lisboa)", "Radio Atlantida", "Radio Botareu (Agueda)", "Radio Cascais (Lisboa)", "Radio Cidade FM", "Radio Comercial", "Radio Festival", "Radio Marcoense (Marco De Canaveses)", "Radio Marginal", "Radio No Ar (Porto)", "Radio Nova", "Radio Nova Era (Vila Nova de Gaia)", "Radio Nove3cinco (Povoa De Lanhoso)", "Radio Orbital (Lisboa)", "Radio Radar (Lisboa)", "Radio RDP africa (Lisboa)", "Radio RFM", "Radio Santiago (Guimaraes)", "Radio Sao Miguel", "Radio TSF (Lisboa)", "Radio Universitaria do Minho (Braga)", "Radio Vizela", "RDP Internacional", "Renascença", "M80", "Rádio Meo Sudoeste (Lisboa)", "Onda Viva", "Rádio Oxigénio", "Rádio SBSR (Lisboa)", "Rádio Toca a Dançar", "Smooth FM (Lisboa)", "Super FM (Almada)", "Vodafone FM (Lisboa)"
];

static DIVISOES: &[&str] = &[
    "Quarto", "Cozinha", "Sala de estar", "Casa de banho", "Jardim", "Garagem", "Escritorio", "Sala de jantar"
];

fn main() -> std::io::Result<()> {
    let args: Vec<String> = env::args().collect();
    match args.len() {
        2 => {
            let f = File::options()
                .write(true)
                .create(true)
                .open(args[1].as_str())?;

            let mut wr = BufWriter::new(f);
            let mut rng = thread_rng();
            
            for f in FORNECEDORES {
                write!(wr, "Fornecedor:{}\n", f)
                    .expect("Fornecedor não escrito");
            }

            let casas: usize = rng.gen_range(60000..64000);
            for _i in 0..=casas {
                generate_casas(&mut wr, &mut rng)
                    .expect("Casa não gerada");

                let divisoes: usize = rng.gen_range(2..=8);
                for j in 0..=divisoes {
                    let mut divs_in_use: [u8; 8] = [1,0,0,1,0,0,0,0];

                    match j {
                        0 => {
                            write!(wr, "Divisao:Quarto\n")?;
                        }
                        1 => {
                            write!(wr, "Divisao:Casa de banho\n")?;
                        }
                        _ => {
                            let current = generate_divisoes(&mut wr, &mut rng, &mut divs_in_use);
                            divs_in_use[current] += 1;
                        }
                    }
                    
                    let dispositivos: usize = rng.gen_range(1..=5);
                    for _k in 0..dispositivos {
                        generate_dipositivos(&mut wr, &mut rng)?;
                    }
                }
            }
            
            wr.flush()?;
            Ok(())
        }
        _ => {
            eprintln!("Invalid number of arguments, valid inputs include [PROGRAM][FILEPATH]");
            Ok(())
        }
    }
}

fn generate_casas(wr: &mut BufWriter<File>, rng: &mut ThreadRng) -> std::io::Result<()> {
    let mut nifs: HashSet<u64> = HashSet::new();
    
    let nif = loop {
        let nif = rng.gen_range(100000000..=280000000);
        if nifs.insert(nif) {
            break nif;
        }
    };
    let conetores = [" e ", " de ", " "];
    write!(
        wr,
        "Casa:{} {}{}{},{},{}\n",
        NOMES.choose(rng).unwrap(),
        APELIDOS.choose(rng).unwrap(),
        conetores.choose(rng).unwrap(),
        APELIDOS.choose(rng).unwrap(),
        nif,
        FORNECEDORES.choose(rng).unwrap()
    )
}

fn generate_divisoes(
    wr: &mut BufWriter<File>,
    rng: &mut ThreadRng,
    divs_in_use: &mut [u8]
) -> usize {
    
    let current: usize = rng.gen_range(0..7);
    if divs_in_use[current] == 0 {
        write!(wr, "Divisao:{}\n", DIVISOES[current])
            .expect("Não escreveu");
    } else {
        write!(wr, "Divisao:{} {}\n", DIVISOES[current], current)
            .expect("Não escreveu");
    }
    return current;
}

fn generate_dipositivos(wr: &mut BufWriter<File>, rng: &mut ThreadRng) -> std::io::Result<()> {
    let dis = ["SmartCamera", "SmartBulb", "SmartSpeaker"].choose(rng).unwrap();
    let mut rng = thread_rng();
    match *dis {
        "SmartCamera" => {
            write!(
                wr,
                "{}:{},{}\n",
                *dis,
                RESOLUCOES.choose(&mut rng).unwrap(),
                rng.gen_range(50..=150)
            )
        }
        "SmartBulb" => {
            write!(
                wr,
                "{}:{},{}\n",
                *dis,
                LIGHTS.choose(&mut rng).unwrap(),
                rng.gen_range(5..=15)
            )
        }
        &_ => {
            write!(
                wr,
                "{}:{},{},{}\n",
                *dis,
                rng.gen_range(0..20),
                RADIOS.choose(&mut rng).unwrap(),
                MARCAS.choose(&mut rng).unwrap()
            )
        }
    }
}