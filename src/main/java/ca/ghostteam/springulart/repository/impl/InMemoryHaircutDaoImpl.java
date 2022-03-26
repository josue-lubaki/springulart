package ca.ghostteam.springulart.repository.impl;

import ca.ghostteam.springulart.model.HaircutModel;
import ca.ghostteam.springulart.repository.HaircutDao;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author Josue Lubaki
 * @version 1.0
 * @since 2022-03-26
 */
@Repository("fake-repository-haircuts")
public class InMemoryHaircutDaoImpl implements HaircutDao {

    private final List<HaircutModel> LIST_HAIRCUTS = new ArrayList<>();

    public InMemoryHaircutDaoImpl() {
        this.initHaircuts();
    }


    @Override
    public List<HaircutModel> findAll() {
        return LIST_HAIRCUTS;
    }

    @Override
    public Optional<HaircutModel> findById(String id) {
        return LIST_HAIRCUTS
                .stream()
                .filter(haircutModel -> haircutModel.getId().equals(id))
                .findFirst();
    }

    @Override
    public Optional<HaircutModel> save(HaircutModel haircutModel) {
        haircutModel.setId(UUID.randomUUID().toString());
        LIST_HAIRCUTS.add(haircutModel);
        return Optional.of(haircutModel);
    }

    // sample data
    private void initHaircuts() {
        // create fake haircuts
        HaircutModel haircutModel = new HaircutModel();
        // generate UUID for each haircut
        haircutModel.setId(UUID.randomUUID().toString());
        haircutModel.setTitle("Coupe Beckham");
        haircutModel.setDescription("Ce chic \"do conserve une finition soignée sur les bords, tandis que la longueur plus longue à travers le haut est ramenée vers l\"arrière pour un look et une sensation classiques");
        haircutModel.setPrice(120);
        haircutModel.setEstimatedTime("10 min");
        haircutModel.setImageURL("https://hairstyles.thehairstyler.com/hairstyle_views/front_view_images/11732/original/David-Beckham.jpg");
        LIST_HAIRCUTS.add(haircutModel);

        HaircutModel haircutModel1 = new HaircutModel();
        haircutModel1.setId(UUID.randomUUID().toString());
        haircutModel1.setTitle("360 Waves");
        haircutModel1.setDescription("La coupe de cheveux des vagues est une coupe à la mode. Pour des vagues complètes à 360 °, obtenez un fondu effilé qui ne coupe que les favoris et le décolleté");
        haircutModel1.setPrice(100);
        haircutModel1.setEstimatedTime("50 min");
        haircutModel1.setImageURL( "https://www.menshairstyletrends.com/wp-content/uploads/2020/12/Taper-Fade-with-Waves-braidsmasterdorian.jpg");
        LIST_HAIRCUTS.add(haircutModel1);

        HaircutModel haircutModel2 = new HaircutModel();
        haircutModel2.setId(UUID.randomUUID().toString());
        haircutModel2.setTitle("Jin's Messy");
        haircutModel2.setDescription("Même si le membre du BTS peut être connu pour ses teintures capillaires colorées, nous le voyons ici avec un look plus naturel et réservé qui favorise ses traits.");
        haircutModel2.setPrice(60);
        haircutModel2.setEstimatedTime("20 min");
        haircutModel2.setImageURL("https://haircutinspiration.com/wp-content/uploads/Jins-Messy-Curtain-Hair-e1535448501869.jpg");
        LIST_HAIRCUTS.add(haircutModel2);

        HaircutModel haircutModel3 = new HaircutModel();
        haircutModel3.setId(UUID.randomUUID().toString());
        haircutModel3.setTitle("dread locks");
        haircutModel3.setDescription("Assez souvent, les gars trouvent qu\"il est beaucoup plus facile de gérer les dreads courtes que les longues. Dread lock est un beau style qui nécessite peu d\"entretien");
        haircutModel3.setPrice(80);
        haircutModel3.setEstimatedTime("45 min");
        haircutModel3.setImageURL("https://haircutinspiration.com/wp-content/uploads/Jins-Messy-Curtain-Hair-e1535448501869.jpg");
        LIST_HAIRCUTS.add(haircutModel3);

        HaircutModel haircutModel4 = new HaircutModel();
        haircutModel4.setId(UUID.randomUUID().toString());
        haircutModel4.setTitle("Mohawk Burst Fade");
        haircutModel4.setDescription("Après tout, le fondu mohawk éclaté est une coupe de cheveux élégante et flatteuse lorsqu\"il est fait correctement. Le burst fade mohawk, également connu sous le nom de fondu du sud de la France");
        haircutModel4.setPrice(35);
        haircutModel4.setEstimatedTime("25 min");
        haircutModel4.setImageURL( "https://www.menshairstylesnow.com/wp-content/uploads/2020/06/Odell-Beckham-Jr-Haircut.jpg");
        LIST_HAIRCUTS.add(haircutModel4);

        HaircutModel haircutModel5 = new HaircutModel();
        haircutModel5.setId(UUID.randomUUID().toString());
        haircutModel5.setTitle("La coupe à la new-yorkaise");
        haircutModel5.setDescription("La coupe de cheveux homme mi long la plus répandue ces derniers temps est la coupe Undercut, similaire de la coupe pompadour. Cette coupe de cheveux est tendance.");
        haircutModel5.setPrice(45);
        haircutModel5.setEstimatedTime("35 min");
        haircutModel5.setImageURL("https://i.pinimg.com/564x/17/bb/8d/17bb8d423273c7ee8ea3849d94c6692e.jpg");
        LIST_HAIRCUTS.add(haircutModel5);

        HaircutModel haircutModel6 = new HaircutModel();
        haircutModel6.setId(UUID.randomUUID().toString());
        haircutModel6.setTitle("Coupe Undercut");
        haircutModel6.setDescription("Cette coupe représente une coupe carrée, mais un peu plus longue que le modèle classique. cette coiffure est travaillée sur deux longueurs, révélant des côtés courts et une nuque dégagée ainsi qu\"une masse capillaire");
        haircutModel6.setPrice(50);
        haircutModel6.setEstimatedTime("20 min");
        haircutModel6.setImageURL("https://archzine.fr/wp-content/uploads/2017/10/coiffure-ame%CC%81ricaine-de%CC%81grade%CC%81-coupe-de-cheveux-homme-court-sur-les-cote%CC%81s-long-dessus.jpg");
        LIST_HAIRCUTS.add(haircutModel6);

        HaircutModel haircutModel7 = new HaircutModel();
        haircutModel7.setId(UUID.randomUUID().toString());
        haircutModel7.setTitle("Coupe Dégradé");
        haircutModel7.setDescription("Cette coupe représente une coupe carrée, mais un peu plus longue que le modèle classique. cette coiffure est travaillée sur deux longueurs, révélant des côtés courts et une nuque dégagée ainsi qu\"une masse capillaire");
        haircutModel7.setPrice(75);
        haircutModel7.setEstimatedTime("45 min");
        haircutModel7.setImageURL("https://guidelook.fr/wp-content/uploads/2020/06/degrade-homme-curly.jpg");
        LIST_HAIRCUTS.add(haircutModel7);
    }
}
