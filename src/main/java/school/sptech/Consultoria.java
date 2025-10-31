package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;
import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public List<Desenvolvedor> getDesenvolvedores() {
        return desenvolvedores;
    }

    public void setDesenvolvedores(List<Desenvolvedor> desenvolvedores) {
        this.desenvolvedores = desenvolvedores;
    }

    public void contratar(Desenvolvedor desenvolvedor) {
        if (desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public void contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedores.size() < vagas && desenvolvedor.isFullstack()) {
            desenvolvedores.add(desenvolvedor);
        }
    }

    public Double getTotalSalarios() {
        Double soma = 0.0;
        for (Desenvolvedor d : desenvolvedores) {
            soma += d.calcularSalario();
        }
        return soma;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        List<Desenvolvedor> resultado = new ArrayList<>();
        for (Desenvolvedor d : desenvolvedores) {
            if (d.calcularSalario() >= salario) {
                resultado.add(d);
            }
        }
        return resultado;
    }

    public Desenvolvedor buscarMenorSalario() {
        if (desenvolvedores.isEmpty()) {
            return null;
        }
        Desenvolvedor menor = desenvolvedores.get(0);
        for (Desenvolvedor d : desenvolvedores) {
            if (d.calcularSalario() < menor.calcularSalario()) {
                menor = d;
            }
        }
        return menor;
    }


    public Integer qtdDesenvolvedoresMobile() {
        int total = 0;
        for (Desenvolvedor d : desenvolvedores) {
            if (d instanceof DesenvolvedorMobile) {
                total++;
            }
        }
        return total;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> resultado = new ArrayList<>();
        for (Desenvolvedor d : desenvolvedores) {
            if (d instanceof DesenvolvedorWeb) {
                DesenvolvedorWeb web = (DesenvolvedorWeb) d;
                if ((web.getBackend() != null && web.getBackend().equalsIgnoreCase(tecnologia)) ||
                        (web.getFrontend() != null && web.getFrontend().equalsIgnoreCase(tecnologia)) ||
                        (web.getSgbd() != null && web.getSgbd().equalsIgnoreCase(tecnologia))) {
                    resultado.add(d);
                }
            } else if (d instanceof DesenvolvedorMobile) {
                DesenvolvedorMobile mob = (DesenvolvedorMobile) d;
                if ((mob.getPlataforma() != null && mob.getPlataforma().equalsIgnoreCase(tecnologia)) ||
                        (mob.getLinguagem() != null && mob.getLinguagem().equalsIgnoreCase(tecnologia))) {
                    resultado.add(d);
                }
            }
        }
        return resultado;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        Double total = 0.0;
        for (Desenvolvedor d : desenvolvedores) {
            if (d instanceof DesenvolvedorWeb) {
                DesenvolvedorWeb web = (DesenvolvedorWeb) d;
                if ((web.getBackend() != null && web.getBackend().equalsIgnoreCase(tecnologia)) ||
                        (web.getFrontend() != null && web.getFrontend().equalsIgnoreCase(tecnologia)) ||
                        (web.getSgbd() != null && web.getSgbd().equalsIgnoreCase(tecnologia))) {
                    total += web.calcularSalario();
                }
            } else if (d instanceof DesenvolvedorMobile) {
                DesenvolvedorMobile mob = (DesenvolvedorMobile) d;
                if ((mob.getPlataforma() != null && mob.getPlataforma().equalsIgnoreCase(tecnologia)) ||
                        (mob.getLinguagem() != null && mob.getLinguagem().equalsIgnoreCase(tecnologia))) {
                    total += mob.calcularSalario();
                }
            }
        }
        return total;
    }
}
