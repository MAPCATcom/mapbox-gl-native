var spec = module.exports = require('../mapbox-gl-js/src/style-spec/reference/v8');

// Make temporary modifications here when Native doesn't have all features that JS has.

delete spec.layer.type.values.heatmap;
delete spec.layer.type.values.hillshade;
