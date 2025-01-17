package dev.kilua.compose.ui.geometry

// Masks everything but the sign bit
const val DualUnsignedFloatMask = 0x7fffffff_7fffffffL

// Any value greater than this is a NaN
const val FloatInfinityBase = 0x7f800000

// Same as above, but for floats packed in a Long
const val DualFloatInfinityBase = 0x7f800000_7f800000L

// Same as Offset/Size.Unspecified.packedValue, but avoids a getstatic
const val UnspecifiedPackedFloats = 0x7fc00000_7fc00000L // NaN_NaN

// 0x80000000_80000000UL.toLong() but expressed as a const value
// Mask for the sign bit of the two floats packed in a long
const val DualFloatSignBit = -0x7fffffff_80000000L

// Set the highest bit of each 32 bit chunk in a 64 bit word
const val Uint64High32 = -0x7fffffff_80000000L

// Set the lowest bit of each 32 bit chunk in a 64 bit word
const val Uint64Low32 = 0x00000001_00000001L

// Encodes the first valid NaN in each of the 32 bit chunk of a 64 bit word
const val DualFirstNaN = 0x7f800001_7f800001L

// Set all the significand bits for each 32 bit chunk in a 64 bit word
const val DualLoadedSignificand = 0x007fffff_007fffffL
