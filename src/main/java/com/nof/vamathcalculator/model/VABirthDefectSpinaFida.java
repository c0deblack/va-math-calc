/**
 * by c0deblack 2022
 * https://github.com/c0deblack
 */
package com.nof.vamathcalculator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VABirthDefectSpinaFida implements VAMathBirthDefectChild {
    private String m_short_name = null;
    private Integer m_disability_level = null;
    public static final Integer MAX_LEVEL = 3;
    public static final Integer MIN_LEVEL = 1;
    private final List<Double> compensation_list = Arrays.asList(362.00, 1231.00, 2096.00);

    VABirthDefectSpinaFida(Integer level, String short_name) {
        if(level >= MIN_LEVEL && level <= MAX_LEVEL) {
            m_disability_level = level;

            if(short_name != null && short_name.length() > 0){
                m_short_name = short_name;
            } else {
                throw new IllegalArgumentException("Invalid short name provided. The name must be a valid string.");
            }
        } else {
            throw new IllegalStateException("Spina Bifida birth defects are rated from levels 1 to 3. Invalid level given!");
        }
    }

    @Override
    public String get_short_name() { return m_short_name; }

    @Override
    public Double get_compensation() {
        return compensation_list.get(m_disability_level - 1);
    }

    @Override
    public Integer get_level() {
        return m_disability_level;
    }
}
