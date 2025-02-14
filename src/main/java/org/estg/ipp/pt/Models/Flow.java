package org.estg.ipp.pt.Models;

import org.estg.ipp.pt.Enums.FlowCategory;
import org.estg.ipp.pt.Enums.Type;
import org.estg.ipp.pt.Enums.UnitType;
import org.estg.ipp.pt.Interfaces.Models.FlowInterface;

import java.util.List;
import java.util.Map;

/**
 * A classe {@code Flow} representa um fluxo de dados dentro de um processo. Ela encapsula as informações
 * como a categoria do fluxo, o nome, o tipo, o valor, a unidade e a taxa de perda.
 *
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Representação de diferentes tipos de fluxos (e.g., transporte, emissões, materiais).</li>
 *   <li>Validação de entradas para garantir consistência da classificação dos mesmos.</li>
 * </ul>
 *
 * <p><b>Estrutura Interna:</b></p>
 * <ul>
 *   <li>{@code category}: Categoria do fluxo (e.g., MATERIAL, TRANSPORT).</li>
 *   <li>{@code name}: Nome do fluxo.</li>
 *   <li>{@code type}: Tipo do fluxo (INPUT ou OUTPUT).</li>
 *   <li>{@code value}: Valor quantitativo do fluxo.</li>
 *   <li>{@code unit}: Unidade do fluxo (e.g., KG, KWH).</li>
 *   <li>{@code lossRate}: Taxa de perda associada ao fluxo.</li>
 * </ul>
 *
 * <p>Essa classe implementa a interface {@link FlowInterface}, que define os métodos para manipulação de fluxos.</p>
 *
 * @see FlowCategory
 * @see Type
 * @see UnitType
 */
public class Flow implements FlowInterface {

    /**
     * Categoria do fluxo, ou seja, o tipo de recurso ou atividade que ele representa.
     */
    FlowCategory category;

    /**
     * Nome do fluxo, utilizado para identificação.
     */
    String name;

    /**
     * Tipo do fluxo, indica se é uma entrada ou saída.
     */
    Type type;

    /**
     * Valor quantitativo associado ao fluxo.
     */
    double value;

    /**
     * Unidade do fluxo, representa a métrica utilizada (e.g., KG, KM, KWH).
     */
    UnitType unit;

    /**
     * Nome da categoria, utilizado para identificação da categoria de fluxo.
     */
    String categoryName;

    /**
     * Taxa de perda associada ao fluxo, é expressa como uma percentagem.
     */
    double lossRate;

    /**
     * Construtor da classe {@code Flow}.
     *
     * <p>Inicializa os atributos do fluxo com base nos parâmetros fornecidos, realizando validação para
     * garantir que os dados estão corretos.</p>
     *
     * @param category Categoria do fluxo.
     * @param name     Nome do fluxo.
     * @param type     Tipo do fluxo (entrada ou saída).
     * @param value    Valor associado ao fluxo.
     * @param unit     Unidade do fluxo.
     * @param lossRate Taxa de perda do fluxo.
     * @throws IllegalArgumentException Se algum parâmetro for inválido.
     */
    public Flow(FlowCategory category, String name, Type type, double value, UnitType unit, double lossRate) {
        validate(category, name, type, value, unit, lossRate);

        this.category = category;
        this.name = name;
        this.type = type;
        this.value = value;
        this.unit = unit;
        this.lossRate = lossRate;
    }

    /**
     * Valida os atributos de um fluxo.
     *
     * @param category Categoria do fluxo.
     * @param name     Nome do fluxo.
     * @param type     Tipo do fluxo.
     * @param value    Valor do fluxo.
     * @param unit     Unidade do fluxo.
     * @param lossRate Taxa de perda do fluxo.
     * @throws IllegalArgumentException Se os dados forem inválidos.
     */
    private static void validate(
            FlowCategory category, String name, Type type,
            double value, UnitType unit, double lossRate) throws IllegalArgumentException {

        validateNotNullOrEmpty(name);
        validateNotNull(category, "Flow category");
        validateNotNull(type, "Flow type");
        validateNotNull(unit, "Flow unit");
        validateNonNegative(value, "Flow value");
        validateNonNegative(lossRate, "Flow loss rate");

        validateUnitForCategory(category, unit);
    }

    /**
     * Valida se o nome não é nulo ou vazio.
     */
    private static void validateNotNullOrEmpty(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Flow name cannot be null or empty");
        }
    }

    /**
     * Valida se o objeto não é nulo.
     */
    private static void validateNotNull(Object obj, String fieldName) {
        if (obj == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    /**
     * Valida se o valor não é negativo.
     */
    private static void validateNonNegative(double value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(fieldName + " cannot be negative");
        }
    }

    /**
     * Valida se a unidade é compatível com a categoria.
     */
    private static void validateUnitForCategory(FlowCategory category, UnitType unit) {
        Map<FlowCategory, List<UnitType>> expectedUnits = Map.of(
                FlowCategory.TRANSPORT, List.of(UnitType.KM, UnitType.NONE),
                FlowCategory.EMISSION, List.of(UnitType.KGCO2EQ, UnitType.KGCO2TONELADAKM, UnitType.GCO2EQKWH, UnitType.NONE),
                FlowCategory.MATERIAL, List.of(UnitType.KG, UnitType.NONE),
                FlowCategory.ENERGY, List.of(UnitType.KWH,UnitType.GCO2EQKWH, UnitType.NONE),
                FlowCategory.WASTE, List.of(UnitType.KG, UnitType.NONE)
        );

        List<UnitType> expectedUnit = expectedUnits.get(category);
        if (expectedUnit != null && !expectedUnit.contains(unit)) {
            throw new IllegalArgumentException(category + " unit must be " + expectedUnit);
        }
    }


    // Métodos getters) e setters

    public FlowCategory getCategory() {
        return category;
    }

    public void setCategory(FlowCategory category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public UnitType getUnit() {
        return unit;
    }

    public void setUnit(UnitType unit) {
        this.unit = unit;
    }

    public double getLossRate() {
        return lossRate;
    }

    public void setLossRate(double lossRate) {
        this.lossRate = lossRate;
    }

    @Override
    public String toString() {
        return "Flow{" +
                "category=" + category +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", value=" + value +
                ", unit=" + unit +
                ", percentage=" + lossRate +
                '}';
    }
}
